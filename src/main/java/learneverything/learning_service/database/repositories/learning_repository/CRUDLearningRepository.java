package learneverything.learning_service.database.repositories.learning_repository;

import learneverything.learning_service.database.entities.LearningEntity;

import java.util.List;

public interface CRUDLearningRepository<T extends LearningEntity> {
    /**
     *
     * @param id
     * @return
     */
    T getLearning(Long id);

    /**
     *
     * @param lessonId
     * @return
     */
    List<T> getLearningByLesson(Integer lessonId);

    /**
     *
     * @param learning
     * @return
     */
    T saveLearning(T learning);

    /**
     *
     * @param learning
     * @return
     */
    List<T> saveLearning(List<T> learning);

    /**
     *
     * @param learning
     */
    void deleteLearning(T learning);

    /**
     *
     * @param learnings
     */
    void deleteLearning(List<T> learnings);
}
