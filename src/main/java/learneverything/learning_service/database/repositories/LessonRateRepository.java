package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.LessonRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRateRepository extends JpaRepository<LessonRateEntity, Long> {
    Optional<LessonRateEntity> findFirstByUserIdAndLessonId(String userId, int lessonId);
}
