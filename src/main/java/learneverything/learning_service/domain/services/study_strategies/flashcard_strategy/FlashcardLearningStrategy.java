package learneverything.learning_service.domain.services.study_strategies.flashcard_strategy;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.LearningProgressEntity;
import learneverything.learning_service.database.repositories.LearningProgressRepository;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.learning_result.LearningResultDTO;
import learneverything.learning_service.domain.dtos.learning_result.LessonResultDTO;
import learneverything.learning_service.domain.dtos.learning_result.QuestionResultDTO;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.enums.LearningType;
import learneverything.learning_service.domain.services.LessonService;
import learneverything.learning_service.domain.services.study_strategies.IStudyStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlashcardLearningStrategy implements IStudyStrategy {
    private final LessonService lessonService;
    private final LearningProgressRepository learningProgressRepository;

    @Override
    public List<Object> learn(Integer lessonId, String userId) {
        LessonDTO lesson = lessonService.get(lessonId);
        if (!lesson.getLearningType().equals(LearningType.QUESTION.getName())){
            throw new BaseException(Error.INVALID_STRATEGY,String.valueOf(lessonId));
        }

        List<Long> learningIds = lesson.getListLearning()
                .stream()
                .map(LearningDTO::getId)
                .toList();

        Map<Long, LearningProgressEntity> learningProgressMap = learningProgressRepository.findByLearningIdIn(learningIds)
                .stream()
                .collect(Collectors.toMap(LearningProgressEntity::getLearningId, e->e));

        List<LearningProgressEntity> learningProgressEntities = new ArrayList<>();

        for (LearningDTO learning : lesson.getListLearning()){
            if (learningProgressMap.containsKey(learning.getId())){
                LearningProgressEntity learningProgressEntity = learningProgressMap.get(learning.getId());
                learningProgressEntities.add(learningProgressEntity);

            }
        }



        return List.of(learningProgressEntities);
    }

    @Override
    public Object evaluate(LessonResultDTO result, String userId) {
        return null;
    }
}
