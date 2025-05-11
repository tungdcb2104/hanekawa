package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.learning_entities.question.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
    @Query(value = """
            SELECT * FROM question
            WHERE lesson_id = :lesson_id
            """,nativeQuery = true)
    List<QuestionEntity> getQuestionsOfLesson(@Param("lesson_id") Integer lessonId);
}
