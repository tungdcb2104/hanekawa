package learneverything.learning_service.domain.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "study-progress-service",url = "http://localhost:8081")
public interface StudyProgressFeign {

    @GetMapping("/api/v1/lesson/{id}/progress")
    String getLessonProgress(Integer lessonId);
}
