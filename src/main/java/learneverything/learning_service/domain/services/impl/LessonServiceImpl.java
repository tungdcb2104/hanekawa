package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.database.entities.LessonEntity;
import learneverything.learning_service.database.repositories.LessonRepository;
import learneverything.learning_service.domain.enums.LearningType;
import learneverything.learning_service.domain.mappers.LearningMapper;
import learneverything.learning_service.domain.services.learning_repository.ICRUDLearningService;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.mappers.LessonMapper;
import learneverything.learning_service.domain.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LessonServiceImpl implements LessonService {
    private LessonRepository lessonRepository;
    private LessonMapper lessonMapper;
    private LearningMapper learningMapper;

    private final Map<Class, ICRUDLearningService> learningRepositoryMap = new HashMap<>();

    @Autowired
    public LessonServiceImpl(List<ICRUDLearningService> repositories, LessonRepository lessonRepository, LessonMapper lessonMapper,LearningMapper learningMapper){
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
        this.learningMapper = learningMapper;
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

        return lessonDTO;
    }

    @Override
    public LessonDTO create(LessonDTO lesson) {
        LessonEntity lessonEntity = lessonMapper.dtoToEntity(lesson);
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
        LessonEntity lessonEntity = lessonRepository.findById(lesson.getId())
                .orElseThrow(() -> new BaseException(Error.NOT_FOUND_LESSON, String.valueOf(lesson.getId())));

        lessonEntity.setTitle(lesson.getTitle());
        lessonEntity.setChapterId(lesson.getChapterId());
        lessonEntity.setDescription(lesson.getDescription());
        lessonEntity.setLearningType(lesson.getLearningType());

        lessonRepository.save(lessonEntity);

        String type = lessonEntity.getLearningType();
        LearningType learningType = LearningType.getLearningTypeByName(type);
        ICRUDLearningService repository = learningRepositoryMap.get(learningType.getCrudServiceClass());

        if (Objects.nonNull(repository)) {
            List<LearningEntity> existingLearnings = repository.getLearningByLesson(lesson.getId());
            System.out.println(existingLearnings);
            List<LearningDTO> newLearnings = lesson.getListLearning();
            System.out.println(newLearnings);

            // Find items to delete (exist in DB but not in new list)
            List<LearningEntity> learningsToDelete = existingLearnings.stream()
                    .filter(existing -> newLearnings.stream()
                            .noneMatch(newItem -> Objects.nonNull(newItem.getId())
                                    && newItem.getId().equals(existing.getId())))
                    .toList();
            System.out.println(learningsToDelete);

            // Delete unnecessary items
            if (!learningsToDelete.isEmpty()) {
                repository.deleteLearning(learningsToDelete);
            }

            // Update existing and add new items
            List<LearningEntity> learningsToSave = newLearnings.stream()
                    .map(dto -> {
                        LearningEntity entity = learningMapper.dtoToEntity(dto);
                        entity.setLessonId(lessonEntity.getId());

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
}
