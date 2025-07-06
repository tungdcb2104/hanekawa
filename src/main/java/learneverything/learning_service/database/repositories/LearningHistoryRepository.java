package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.LearningHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningHistoryRepository extends JpaRepository<LearningHistoryEntity,Long> {
}
