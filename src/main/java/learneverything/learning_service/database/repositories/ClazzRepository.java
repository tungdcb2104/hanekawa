package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.ClazzEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends JpaRepository<ClazzEntity, Integer> { }
