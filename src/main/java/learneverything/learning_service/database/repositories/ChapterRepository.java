package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity,Integer> {
    @Query(value = """
            SELECT * FROM CHAPTER
            WHERE clazz_id = :clazz_id
            AND status = 1
            """,nativeQuery = true)
    List<ChapterEntity> findActiveChaptersOfClazz(@Param("clazz_id") Integer clazzId);
}
