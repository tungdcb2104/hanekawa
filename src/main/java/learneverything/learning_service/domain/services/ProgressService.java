package learneverything.learning_service.domain.services;

import org.springframework.stereotype.Service;

@Service
public interface ProgressService {
    Object getProgressByLessonId(Integer lessonId);
    Object getRecentClazz(String userId, Integer quantity);
}
