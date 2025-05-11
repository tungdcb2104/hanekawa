package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.learning_entities.vocabulary.VocabularyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabularyRepository extends JpaRepository<VocabularyEntity,Long> {
}
