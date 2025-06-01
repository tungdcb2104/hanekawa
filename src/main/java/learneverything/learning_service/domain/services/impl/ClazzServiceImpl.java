package learneverything.learning_service.domain.services.impl;

import jakarta.persistence.criteria.Predicate;
import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.ChapterEntity;
import learneverything.learning_service.database.entities.ClazzEntity;
import learneverything.learning_service.database.repositories.ChapterRepository;
import learneverything.learning_service.database.repositories.ClazzRepository;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClazzServiceImpl implements ClazzService {
    private final ClazzRepository clazzRepository;
    private final ClazzMapper clazzMapper;
    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;

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
        clazzRepository.deleteById(id);
        return "Successful !";
    }

    @Override
    public Object update(Integer id,UpdateClazzRequestDTO updateClazzRequest) {
        ClazzEntity clazzEntity = clazzRepository.findById(id)
                .orElseThrow(()->new BaseException(Error.NOT_FOUND_LESSON,id.toString()));

        clazzMapper.updateDtoToEntity(updateClazzRequest,clazzEntity);

        return clazzRepository.save(clazzEntity);
    }

    @Override
    public List<ClazzDTO> getLearningClasses(SearchClazzDTO searchClazzDTO) {
        String userId = CommonUtils.getUserId();
        List<ClazzEntity> allLearningClasses = clazzRepository.getLearningClassesByUserId(userId);
        List<ClazzEntity> learningClasses = clazzRepository
                .findAll(searchLearningClasses(searchClazzDTO,allLearningClasses.stream().map(ClazzEntity::getId).toList()));

        return clazzMapper.toDTOs(learningClasses);
    }

    private Specification<ClazzEntity> searchLearningClasses(SearchClazzDTO searchClazzDTO,List<Integer> currentLearningClasses){
        return ((root, query, criteriaBuilder) -> {
           List<Predicate> predicates = new ArrayList<>();
           predicates.add(criteriaBuilder.in(root.get("id").in(currentLearningClasses)));
           if (searchClazzDTO.getName() != null){
               predicates.add(criteriaBuilder.like(root.get("name"),searchClazzDTO.getName()));
           }

           return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
