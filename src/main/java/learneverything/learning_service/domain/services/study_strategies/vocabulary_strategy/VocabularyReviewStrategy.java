package learneverything.learning_service.domain.services.study_strategies.vocabulary_strategy;

import learneverything.learning_service.domain.services.study_strategies.IStudyStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyReviewStrategy implements IStudyStrategy {
    @Override
    public List<Object> learn(Integer lessonId) {
        return List.of();
    }
}
