package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.domain.services.ProgressService;
import org.springframework.stereotype.Service;

@Service
public class ProgressServiceImpl implements ProgressService {
    @Override
    public Object getProgressByLessonId(Integer lessonId) {
        return null;
    }

    @Override
    public Object getRecentClazz(String userId, Integer quantity) {
        return null;
    }
}
