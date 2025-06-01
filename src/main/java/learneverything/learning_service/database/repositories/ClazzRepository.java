package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.ClazzEntity;
import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClazzRepository extends JpaRepository<ClazzEntity, Integer>, JpaSpecificationExecutor<ClazzEntity> {

    @Query(value = """
            SELECT * FROM clazz c
            JOIN enrollment e IN e.clazz_id = c.id
            WHERE e.user_id = :userId
            AND status = 1
            """,nativeQuery = true)
    List<ClazzEntity> getLearningClassesByUserId(@Param("userId") String userId);
}
