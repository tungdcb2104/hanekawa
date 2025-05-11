package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.services.LessonService;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {

    @Override
    public LessonDTO get(Integer id) {
        return null;
    }

    @Override
    public LessonDTO create(LessonDTO lesson) {
        return null;
    }

    @Override
    public LessonDTO update(LessonDTO lesson) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return "";
    }
}
