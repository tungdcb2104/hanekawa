package learneverything.learning_service.domain.services.study_strategies.vocabulary_strategy;

import learneverything.learning_service.domain.services.study_strategies.IStudyStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularySpacedRepetitionStrategy implements IStudyStrategy{
    @Override
    public List<Object> learn(Integer lessonId, String userId) {
        return List.of();
    }
}
