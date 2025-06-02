package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Long> {
    List<EnrollmentEntity> findByUserIdAndClazzId(String userId,Integer clazzId);
}
