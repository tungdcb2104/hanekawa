package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.LearningProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningProgressRepository extends JpaRepository<LearningProgressEntity,Long> {
    List<LearningProgressEntity> findByLearningIdIn(List<Long> ids);
}
