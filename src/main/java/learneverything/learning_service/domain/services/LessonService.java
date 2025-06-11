package learneverything.learning_service.domain.services;

import jakarta.validation.Valid;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.dtos.lesson.VoteLessonDTO;
import org.springframework.stereotype.Service;

@Service
public interface LessonService {
    LessonDTO get(Integer id);
    LessonDTO create(LessonDTO lesson);
    LessonDTO update(LessonDTO lesson);
    String delete(Integer id);
    Object voteLesson(@Valid VoteLessonDTO request);
}
