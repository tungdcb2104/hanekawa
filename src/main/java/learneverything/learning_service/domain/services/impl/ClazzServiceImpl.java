package learneverything.learning_service.domain.services.impl;

import jakarta.persistence.criteria.Predicate;
import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.ChapterEntity;
import learneverything.learning_service.database.entities.ClazzEntity;
import learneverything.learning_service.database.entities.LessonEntity;
import learneverything.learning_service.database.entities.PinClazzEntity;
import learneverything.learning_service.database.repositories.ChapterRepository;
import learneverything.learning_service.database.repositories.ClazzRepository;
import learneverything.learning_service.database.repositories.LessonRepository;
import learneverything.learning_service.database.repositories.PinClazzRepository;
import learneverything.learning_service.domain.dtos.BaseResponse;
import learneverything.learning_service.domain.dtos.chapter.ChapterDTO;
import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.CreateClazzRequestDTO;
import learneverything.learning_service.domain.dtos.clazz.SearchClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.UpdateClazzRequestDTO;
import learneverything.learning_service.domain.mappers.ChapterMapper;
import learneverything.learning_service.domain.mappers.ClazzMapper;
import learneverything.learning_service.domain.services.ClazzService;
import learneverything.learning_service.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClazzServiceImpl implements ClazzService {
    private final ClazzRepository clazzRepository;
    private final ClazzMapper clazzMapper;
    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;
    private final LessonRepository lessonRepository;
    private final PinClazzRepository pinClazzRepo;

    @Override
    public ClazzDTO get(Integer id) {
        ClazzEntity clazz = clazzRepository.findById(id).orElseThrow(()->new BaseException(Error.NOT_FOUND_LESSON,id.toString()));
        ClazzDTO clazzDTO = clazzMapper.toDTO(clazz);

        List<ChapterEntity> chapterEntities = chapterRepository.findActiveChaptersOfClazz(id);
        if (!CommonUtils.isNullOrEmpty(chapterEntities)){
            List<ChapterDTO> chapterDTOS = chapterEntities.stream().map(chapterMapper::toDTO).toList();
            clazzDTO.setListChapter(chapterDTOS);
        }

        return clazzDTO;
    }

    @Override
    public Object create(CreateClazzRequestDTO createClazzRequest) {
        ClazzEntity clazz = clazzMapper.createDTOToEntity(createClazzRequest);
        clazz.setAuthorId(CommonUtils.getUserId());
        return clazzRepository.save(clazz);
    }

    @Override
    public String delete(Integer id) {
        String userId = CommonUtils.getUserId();
        ClazzEntity clazzEntity = clazzRepository.findById(id).orElseThrow(()->new BaseException(Error.NOT_FOUND_LESSON,id.toString()));
        if (!userId.equals(clazzEntity.getAuthorId())){
            throw new BaseException(Error.FORBIDDEN);
        }
        clazzEntity.setStatus(0);
        List<ChapterEntity> chapterEntities = chapterRepository.findActiveChaptersOfClazz(id);
        chapterEntities.forEach(e->{
            e.setStatus(0);
        });

        List<LessonEntity> lessonEntities = lessonRepository.findByChapterIdIn(chapterEntities.stream().map(ChapterEntity::getId).toList());
        lessonEntities.forEach(e->{
            e.setStatus(0);
        });

        clazzRepository.save(clazzEntity);
        chapterRepository.saveAll(chapterEntities);
        lessonRepository.saveAll(lessonEntities);

        return "Successful !";
    }

    @Override
    public Object update(Integer id,UpdateClazzRequestDTO updateClazzRequest) {
        ClazzEntity clazzEntity = clazzRepository.findById(id)
                .orElseThrow(()->new BaseException(Error.NOT_FOUND_LESSON,id.toString()));

        String userId = CommonUtils.getUserId();
        if (!userId.equals(clazzEntity.getAuthorId())){
            throw new BaseException(Error.FORBIDDEN);
        }
        clazzMapper.updateDtoToEntity(updateClazzRequest,clazzEntity);

        return clazzRepository.save(clazzEntity);
    }

    @Override
    public List<ClazzDTO> getLearningClasses() {
        String userId = CommonUtils.getUserId();
        List<ClazzEntity> allLearningClasses = clazzRepository.getLearningClassesByUserId(userId);
        List<ClazzDTO> returns = clazzMapper.toDTOs(allLearningClasses);
        Map<Integer,ClazzEntity> pinnedClasses = getPinClazz()
                .stream().collect(Collectors.toMap(ClazzEntity::getId, e->e));
        returns.forEach(e->{
            e.setPinned(pinnedClasses.containsKey(e.getId()));
        });


        returns.sort((o1, o2) -> {
            if (o1.getPinned()){
                return -1;
            }else if (o2.getPinned()){
                return 1;
            }else {
                return 0;
            }
        });

        return returns;
    }

    @Override
    public List<ClazzDTO> searchClasses(SearchClazzDTO searchClazzDTO) {
        List<ClazzEntity> clazzEntities = clazzRepository.findAll(searchLearningClasses(searchClazzDTO));
        return clazzMapper.toDTOs(clazzEntities);
    }

    @Override
    public BaseResponse<Void> pin(Integer clazzId) {
        String userId = CommonUtils.getUserId();
        PinClazzEntity pinClazz = pinClazzRepo.findFirstByUserIdAndClazzId(userId, clazzId).orElse(new PinClazzEntity());
        pinClazz.setClazzId(clazzId);
        pinClazz.setUserId(userId);
        pinClazzRepo.save(pinClazz);
        return new BaseResponse<>();
    }

    @Override
    public BaseResponse<Void> unPin(Integer clazzId) {
        String userId = CommonUtils.getUserId();
        pinClazzRepo.findFirstByUserIdAndClazzId(userId, clazzId).ifPresent(pinClazzRepo::delete);
        return new BaseResponse<>();
    }

    @Override
    public List<ClazzEntity> getPinClazz() {
        String userId = CommonUtils.getUserId();
        List<Integer> clazzIds = pinClazzRepo.findByUserId(userId).stream()
                .map(PinClazzEntity::getClazzId)
                .distinct().toList();
        return clazzRepository.findByIdIn(clazzIds);
    }

    private Specification<ClazzEntity> searchLearningClasses(SearchClazzDTO searchClazzDTO){
        return ((root, query, criteriaBuilder) -> {
           List<Predicate> predicates = new ArrayList<>();
           if (searchClazzDTO.getName() != null){
               predicates.add(criteriaBuilder.like(root.get("name"),searchClazzDTO.getName()));
           }

           return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
