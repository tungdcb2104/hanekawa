package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.learning_entities.vocabulary.VocabularyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyRepository extends JpaRepository<VocabularyEntity,Long> {
    @Query(value = """
            SELECT * FROM vocabulary
            WHERE lesson_id = :lessonId
            """,nativeQuery = true)
    public List<VocabularyEntity> getVocabularyByLesson(Integer lessonId);
}
