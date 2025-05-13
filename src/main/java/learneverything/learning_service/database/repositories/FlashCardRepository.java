package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.learning_entities.flashcard.FlashCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashCardRepository extends JpaRepository<FlashCardEntity, Long> {
    @Query(value = """
            SELECT * FROM flashcard
            WHERE lesson_id = :lesson_id
            """,nativeQuery = true)
    List<FlashCardEntity> getFlashcardsByLesson(@Param("lesson_id") Integer lessonId);
}
