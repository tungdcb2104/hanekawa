package learneverything.learning_service.domain.services.study_strategies.question_strategy;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.LearningProgressEntity;
import learneverything.learning_service.database.repositories.LearningProgressRepository;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.learning.question.QuestionDTO;
import learneverything.learning_service.domain.dtos.learning_result.LearningResultDTO;
import learneverything.learning_service.domain.dtos.learning_result.LessonResultDTO;
import learneverything.learning_service.domain.dtos.learning_result.QuestionResultDTO;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.enums.LearningType;
import learneverything.learning_service.domain.services.LessonService;
import learneverything.learning_service.domain.services.study_strategies.IStudyStrategy;
import learneverything.learning_service.domain.services.study_strategies.StrategyType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * question strategy
 * id = 3
 */

@Service
@RequiredArgsConstructor
public class QuestionStrategy implements IStudyStrategy {
    private final LessonService lessonService;
    private final LearningProgressRepository learningProgressRepository;

    @Override
    public List<Object> learn(Integer lessonId,String userId) {
        LessonDTO lesson = lessonService.get(lessonId);
        if (!lesson.getLearningType().equals(LearningType.QUESTION.getName())){
            throw new BaseException(Error.INVALID_STRATEGY,String.valueOf(lessonId));
        }

        return Collections.singletonList(lesson.getListLearning());
    }

    @Override
    public Object evaluate(LessonResultDTO result, String userId) {
        LessonDTO lesson = lessonService.get(result.getLessonId());
        String learningType = lesson.getLearningType();
        StrategyType questionType = StrategyType.findByStrategy(QuestionStrategy.class);
        if (!questionType.getLearningType().getName().equals(learningType)){
            throw new BaseException(Error.INVALID_STRATEGY,String.valueOf(result.getLessonId()));
        }

        List<Long> learningIds = lesson.getListLearning()
                .stream()
                .map(LearningDTO::getId)
                .toList();

        Map<Long,LearningProgressEntity> learningProgressMap = learningProgressRepository.findByLearningIdIn(learningIds)
                .stream()
                .collect(Collectors.toMap(LearningProgressEntity::getLearningId,e->e));

        List<LearningProgressEntity> learningProgressEntities = new ArrayList<>();

        for (LearningResultDTO learningResult : result.getLearningResults()){
            if (learningResult.getClass()!=QuestionResultDTO.class)throw new BaseException(Error.INVALID_LEARNING_TYPE);
            QuestionResultDTO questionResult = (QuestionResultDTO)learningResult;

            LearningProgressEntity learningProgressEntity;
            if (learningProgressMap.containsKey(questionResult.getId())){
                learningProgressEntity = learningProgressMap.get(questionResult.getId());
                if (questionResult.isCorrect()){
                    learningProgressEntity.setProgress(learningProgressEntity.getProgress()+1);
                }else {
                    learningProgressEntity.setProgress(learningProgressEntity.getProgress()-1);
                }
            }else {
                learningProgressEntity = new LearningProgressEntity();
                learningProgressEntity.setLearningId(questionResult.getId());
                learningProgressEntity.setUserId(userId);
                if (questionResult.isCorrect()){
                    learningProgressEntity.setProgress(1);
                }else {
                    learningProgressEntity.setProgress(-1);
                }
            }
            learningProgressEntities.add(learningProgressEntity);
        }

        return learningProgressRepository.saveAll(learningProgressEntities);
    }
}
