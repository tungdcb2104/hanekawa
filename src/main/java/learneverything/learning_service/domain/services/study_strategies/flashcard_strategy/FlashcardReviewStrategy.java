package learneverything.learning_service.domain.services.study_strategies.flashcard_strategy;

import learneverything.learning_service.database.repositories.LearningProgressRepository;
import learneverything.learning_service.domain.dtos.learning_result.LessonResultDTO;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.services.LessonService;
import learneverything.learning_service.domain.services.study_strategies.IStudyStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashcardReviewStrategy implements IStudyStrategy {
    private final LessonService lessonService;
    private final LearningProgressRepository learningProgressRepository;

    @Override
    public List<Object> learn(Integer lessonId, String userId) {
        LessonDTO lesson = lessonService.get(lessonId);

        return List.of();
    }

    @Override
    public Object evaluate(LessonResultDTO result, String userId) {
        return null;
    }
}
