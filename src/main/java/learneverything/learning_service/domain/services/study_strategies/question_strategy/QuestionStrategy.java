package learneverything.learning_service.domain.services.study_strategies.question_strategy;

import learneverything.learning_service.domain.services.study_strategies.IStudyStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionStrategy implements IStudyStrategy {
    @Override
    public List<Object> learn(Integer lessonId) {


        return List.of();
    }
}
