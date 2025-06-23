package learneverything.learning_service.database.repositories;

import learneverything.learning_service.database.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity,Integer> {
    @Query(value = """
        SELECT * FROM lesson
        WHERE chapter_id = :chapter_id
        """, nativeQuery = true)
    List<LessonEntity> getLessonByChapterId(@Param("chapter_id") Integer chapterId);

    List<LessonEntity> findByChapterIdIn(List<Integer> chapterIds);
}
