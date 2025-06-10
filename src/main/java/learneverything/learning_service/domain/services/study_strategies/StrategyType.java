package learneverything.learning_service.domain.services.study_strategies;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.domain.dtos.learning_result.FlashcardProgressResultDTO;
import learneverything.learning_service.domain.dtos.learning_result.FlashcardResultDTO;
import learneverything.learning_service.domain.dtos.learning_result.LearningResultDTO;
import learneverything.learning_service.domain.enums.LearningType;
import learneverything.learning_service.domain.services.study_strategies.flashcard_strategy.FlashcardReviewStrategy;
import learneverything.learning_service.domain.services.study_strategies.flashcard_strategy.FlashcardSimpleStrategy;
import learneverything.learning_service.domain.services.study_strategies.question_strategy.QuestionStrategy;
import learneverything.learning_service.domain.services.study_strategies.vocabulary_strategy.VocabularyReviewStrategy;
import learneverything.learning_service.domain.services.study_strategies.vocabulary_strategy.VocabularySpacedRepetitionStrategy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum StrategyType {
    VOCABULARY_SPACED_REPETITION(1,LearningType.VOCABULARY, VocabularySpacedRepetitionStrategy.class, LearningResultDTO.class),
    VOCABULARY_REVIEW(2,LearningType.VOCABULARY, VocabularyReviewStrategy.class, LearningResultDTO.class),
    QUESTION(3,LearningType.QUESTION, QuestionStrategy.class, LearningResultDTO.class),
    FLASHCARD_SIMPLE(4,LearningType.FLASHCARD, FlashcardSimpleStrategy.class, FlashcardResultDTO.class),
    FLASHCARD_REVIEW(5,LearningType.FLASHCARD, FlashcardReviewStrategy.class, FlashcardProgressResultDTO.class)
    ;

    private final Integer id;
    private final LearningType learningType;
    private final Class strategy;
    private final Class resultClazz;

    StrategyType(Integer id, LearningType learningType, Class strategy,Class resultClazz) {
        this.id = id;
        this.learningType = learningType;
        this.strategy = strategy;
        this.resultClazz = resultClazz;
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
