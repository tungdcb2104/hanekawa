package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.PinClazzEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PinClazzRepository extends JpaRepository<PinClazzEntity, Long> {
    Optional<PinClazzEntity> findFirstByUserIdAndClazzId(int userId, Integer clazzId);

    List<PinClazzEntity> findByUserId(int userId);
}
