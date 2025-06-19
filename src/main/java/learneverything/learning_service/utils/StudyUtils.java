package learneverything.learning_service.utils;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.domain.services.study_strategies.StrategyType;
import learneverything.learning_service.domain.services.study_strategies.flashcard_strategy.FlashcardSimpleStrategy;

public class StudyUtils {
    public static void validateStudyStrategy(String learningType,Integer lessonId,Class strategy){
        StrategyType type = StrategyType.findByStrategy(strategy);
        if (!type.getLearningType().getName().equals(learningType)){
            throw new BaseException(Error.INVALID_STRATEGY,String.valueOf(lessonId));
        }
    }
}
