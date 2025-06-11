package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.database.entities.LessonEntity;
import learneverything.learning_service.database.entities.LessonRateEntity;
import learneverything.learning_service.database.repositories.ChapterRepository;
import learneverything.learning_service.database.repositories.LessonRateRepository;
import learneverything.learning_service.database.repositories.LessonRepository;
import learneverything.learning_service.domain.dtos.BaseResponse;
import learneverything.learning_service.domain.dtos.lesson.VoteLessonDTO;
import learneverything.learning_service.domain.enums.LearningType;
import learneverything.learning_service.domain.mappers.LearningMapper;
import learneverything.learning_service.domain.services.learning_repository.ICRUDLearningService;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.mappers.LessonMapper;
import learneverything.learning_service.domain.services.LessonService;
import learneverything.learning_service.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Setter
public class LessonServiceImpl implements LessonService {
    private static final Logger log = LoggerFactory.getLogger(LessonServiceImpl.class);
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private LessonMapper lessonMapper;
    @Autowired
    private LearningMapper learningMapper;
    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private LessonRateRepository lessonRateRepo;



    private final Map<Class, ICRUDLearningService> learningRepositoryMap = new HashMap<>();

    @Autowired
    public LessonServiceImpl(List<ICRUDLearningService> repositories){
        log.info("[LessonServiceImpl] : Init bean");
        for (ICRUDLearningService repository : repositories){
            learningRepositoryMap.put(repository.getClass(),repository);
        }
    }

    public List<LearningEntity> getLearningByLesson(Integer lessonId, Class clazz){
        ICRUDLearningService repository = learningRepositoryMap.get(clazz);
        if (Objects.isNull(repository)){
            throw new BaseException(Error.INVALID_LESSON);
        }

        return repository.getLearningByLesson(lessonId);
    }


    public void delete(List<LearningEntity> learnings){
        if (Objects.isNull(learnings) || learnings.isEmpty())return;
        Class<? extends LearningEntity> clazz = learnings.get(0).getClass();
        LearningType learningType = LearningType.getLearningTypeByEntityClass(clazz);

        ICRUDLearningService repository = learningRepositoryMap.get(learningType.getCrudServiceClass());
        if (Objects.isNull(repository)){
            throw new BaseException(Error.INVALID_LESSON);
        }
        repository.deleteLearning(learnings);
    }

    @Override
    public LessonDTO get(Integer id) {
        LessonEntity lessonEntity = lessonRepository.findById(id)
                .orElseThrow(()->new BaseException(Error.NOT_FOUND_LESSON,String.valueOf(id)));

        LessonDTO lessonDTO = lessonMapper.entityToDto(lessonEntity);
        String type = lessonEntity.getLearningType();

        LearningType learningType = LearningType.getLearningTypeByName(type);
        ICRUDLearningService repository = learningRepositoryMap.get(learningType.getCrudServiceClass());
        if (Objects.isNull(repository)){
            throw new BaseException(Error.INVALID_LESSON);
        }

        List<LearningEntity> learningEntities = repository.getLearningByLesson(id);
        List<LearningDTO> learningDTOS = learningEntities.stream()
                .map(e->learningMapper.entityToDto(e))
                .toList();

        lessonDTO.setListLearning(learningDTOS);
        int userId = Integer.parseInt(CommonUtils.getUserId());
        LessonRateEntity lessonRate=  lessonRateRepo.findFirstByUserIdAndLessonId(userId, id).orElse(new LessonRateEntity());
        lessonDTO.setRate(lessonRate.getRate());
        return lessonDTO;
    }

    @Override
    public LessonDTO create(LessonDTO lesson) {
        if (CommonUtils.isNullOrEmpty(lesson.getLearningType())){
            throw new BaseException(Error.INVALID_LESSON);
        }
        LessonEntity lessonEntity = lessonMapper.dtoToEntity(lesson);
        // Check if the chapter exists
        if(chapterRepository.findById(lesson.getChapterId()).isEmpty()){
            throw new BaseException(Error.NOT_FOUND_CHAPTER, lesson.getChapterId().toString());
        }

        lessonRepository.save(lessonEntity);

        lesson.setId(lessonEntity.getId());

        List<LearningDTO> learningDTOs = lesson.getListLearning();
        List<LearningEntity> learningEntities = learningDTOs.stream()
                .map(e->{
                    LearningEntity entity = learningMapper.dtoToEntity(e);
                    entity.setLessonId(lessonEntity.getId());
                    return entity;
                })
                .toList();

        save(learningEntities);

        return lesson;
    }


    public List<LearningEntity> save(List<LearningEntity> learnings){
        if (Objects.isNull(learnings) || learnings.isEmpty())return new ArrayList<>();
        Class<? extends LearningEntity> clazz = learnings.get(0).getClass();

        LearningType learningType = LearningType.getLearningTypeByEntityClass(clazz);

        ICRUDLearningService repository = learningRepositoryMap.get(learningType.getCrudServiceClass());
        if (Objects.isNull(repository)){
            throw new BaseException(Error.INVALID_LESSON);
        }

        return repository.saveLearning(learnings);
    }

    @Override
    public LessonDTO update(LessonDTO lesson) {
        if (!lessonRepository.existsById(lesson.getId())) {
            throw new BaseException(Error.NOT_FOUND_LESSON, String.valueOf(lesson.getId()));
        }
        LessonEntity lessonEntity = lessonMapper.dtoToEntity(lesson);
        // Check if the chapter exists
        if(chapterRepository.findById(lesson.getChapterId()).isEmpty()){
            throw new BaseException(Error.NOT_FOUND_CHAPTER, lesson.getChapterId().toString());
        }

        lessonRepository.save(lessonEntity);

        String type = lessonEntity.getLearningType();
        LearningType learningType = LearningType.getLearningTypeByName(type);
        ICRUDLearningService repository = learningRepositoryMap.get(learningType.getCrudServiceClass());

        if (Objects.nonNull(repository)) {
            List<LearningEntity> existingLearnings = repository.getLearningByLesson(lesson.getId());
            List<LearningDTO> newLearnings = lesson.getListLearning();

            // Find items to delete (exist in DB but not in new list)
            List<LearningEntity> learningsToDelete = existingLearnings.stream()
                    .filter(existing -> newLearnings.stream()
                            .noneMatch(newItem -> Objects.nonNull(newItem.getId())
                                    && newItem.getId().equals(existing.getId())))
                    .toList();

            // Delete unnecessary items
            if (!learningsToDelete.isEmpty()) {
                repository.deleteLearning(learningsToDelete);
            }

            // Update existing and add new items
            Integer lessonId = lessonEntity.getId();
            List<LearningEntity> learningsToSave = newLearnings.stream()
                    .map(dto -> {
                        LearningEntity entity = learningMapper.dtoToEntity(dto);
                        entity.setLessonId(lessonId);

                        // If this is an existing learning, find and preserve its ID
                        if (dto.getId() != null) {
                            existingLearnings.stream()
                                    .filter(existing -> existing.getId().equals(dto.getId()))
                                    .findFirst()
                                    .ifPresent(existing -> entity.setId(existing.getId()));
                        }
                        return entity;
                    })
                    .toList();

            save(learningsToSave);
        }

        return lesson;
    }

    @Override
    public String delete(Integer id) {
        LessonEntity lessonEntity = lessonRepository.findById(id)
                .orElseThrow(() -> new BaseException(Error.NOT_FOUND_LESSON, String.valueOf(id)));

        // Delete learning entities associated with the lesson
        String type = lessonEntity.getLearningType();
        LearningType learningType = LearningType.getLearningTypeByName(type);
        ICRUDLearningService repository = learningRepositoryMap.get(learningType.getCrudServiceClass());
        delete(repository.getLearningByLesson(id));

        lessonRepository.delete(lessonEntity);

        return "Lesson with ID " + id + " deleted successfully.";
    }

    @Override
    public Object voteLesson(VoteLessonDTO request) {
        int userId = Integer.parseInt(CommonUtils.getUserId());

        if (request.getRate() == null) {
            lessonRateRepo.findFirstByUserIdAndLessonId(userId, request.getLessonId())
                    .ifPresent(lessonRateRepo::delete);
            return new BaseResponse<>();
        }

        LessonRateEntity lessonRate = lessonRateRepo
                .findFirstByUserIdAndLessonId(userId, request.getLessonId())
                .orElse(new LessonRateEntity());
        lessonRate.setRate(request.getRate());
        lessonRate.setUserId(userId);
        lessonRate.setLessonId(request.getLessonId());
        lessonRateRepo.save(lessonRate);
        return new BaseResponse<>();
    }
}
