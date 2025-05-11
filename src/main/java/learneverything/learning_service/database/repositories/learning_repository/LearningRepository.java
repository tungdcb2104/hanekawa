package learneverything.learning_service.database.repositories.learning_repository;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.LearningEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class LearningRepository {
    private final Map<Class,CRUDLearningRepository> learningRepositoryMap = new HashMap<>();

    @Autowired
    public LearningRepository(List<CRUDLearningRepository> repositories){
        for (CRUDLearningRepository repository : repositories){
            learningRepositoryMap.put(repository.getClass(),repository);
        }
    }

    public List<LearningEntity> getLearningByLesson(Integer lessonId,Class clazz){
        CRUDLearningRepository repository = learningRepositoryMap.get(clazz);
        if (Objects.isNull(repository)){
            throw new BaseException(Error.INVALID_LESSON);
        }

        return repository.getLearningByLesson(lessonId);
    }

    public List<LearningEntity> save(List<LearningEntity> learnings){
        if (Objects.isNull(learnings) || learnings.isEmpty())return new ArrayList<>();
        Class<? extends LearningEntity> clazz = learnings.get(0).getClass();

        CRUDLearningRepository repository = learningRepositoryMap.get(clazz);
        if (Objects.isNull(repository)){
            throw new BaseException(Error.INVALID_LESSON);
        }

        return repository.saveLearning(learnings);
    }

    public void delete(List<LearningEntity> learnings){
        if (Objects.isNull(learnings) || learnings.isEmpty())return;
        Class<? extends LearningEntity> clazz = learnings.get(0).getClass();

        CRUDLearningRepository repository = learningRepositoryMap.get(clazz);
        if (Objects.isNull(repository)){
            throw new BaseException(Error.INVALID_LESSON);
        }
        repository.deleteLearning(learnings);
    }

}
