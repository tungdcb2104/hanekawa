package learneverything.learning_service.domain.services;

import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import org.springframework.stereotype.Service;

@Service
public interface LessonService {
    LessonDTO get(Integer id);
    LessonDTO create(LessonDTO lesson);
    LessonDTO update(LessonDTO lesson);
    String delete(Integer id);
}
