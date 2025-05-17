package learneverything.learning_service.domain.services.study_strategies;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.domain.enums.LearningType;
import learneverything.learning_service.domain.services.study_strategies.question_strategy.QuestionStrategy;
import learneverything.learning_service.domain.services.study_strategies.vocabulary_strategy.VocabularyReviewStrategy;
import learneverything.learning_service.domain.services.study_strategies.vocabulary_strategy.VocabularySpacedRepetitionStrategy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum StrategyType {
    VOCABULARY_SPACED_REPETITION(1,LearningType.VOCABULARY, VocabularySpacedRepetitionStrategy.class),
    VOCABULARY_REVIEW(2,LearningType.VOCABULARY, VocabularyReviewStrategy.class),
    QUESTION(3,LearningType.QUESTION, QuestionStrategy.class)
    ;

    private final Integer id;
    private final LearningType learningType;
    private final Class strategy;

    StrategyType(Integer id, LearningType learningType, Class strategy) {
        this.id = id;
        this.learningType = learningType;
        this.strategy = strategy;
    }

    public static StrategyType findById(Integer id){
        for (StrategyType type : StrategyType.values()){
            if (type.getId().equals(id)){
                return type;
            }
        }
        throw new BaseException(Error.NOT_FOUND_STRATEGY);
    }

    public static List<StrategyType> findByLearningType(LearningType learningType){
        List<StrategyType> strategyTypes = new ArrayList<>();
        for (StrategyType type : StrategyType.values()){
            if (type.getLearningType().equals(learningType)){
                strategyTypes.add(type);
            }
        }
        return strategyTypes;
    }

    public static StrategyType findByStrategy(Class clazz){
        for (StrategyType type : StrategyType.values()){
            if (type.getStrategy().equals(clazz)){
                return type;
            }
        }

        throw new BaseException(Error.NOT_FOUND_STRATEGY);
    }
}
