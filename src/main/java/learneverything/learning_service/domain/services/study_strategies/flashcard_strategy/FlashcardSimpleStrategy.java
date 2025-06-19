package learneverything.learning_service.domain.services.study_strategies.flashcard_strategy;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.LearningProgressEntity;
import learneverything.learning_service.database.entities.learning_entities.flashcard.FlashCardEntity;
import learneverything.learning_service.database.repositories.LearningProgressRepository;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.learning.flashcard.FlashCardDTO;
import learneverything.learning_service.domain.dtos.learning_result.FlashcardResultDTO;
import learneverything.learning_service.domain.dtos.learning_result.LearningResultDTO;
import learneverything.learning_service.domain.dtos.learning_result.LessonResultDTO;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.enums.LearningType;
import learneverything.learning_service.domain.services.LessonService;
import learneverything.learning_service.domain.services.study_strategies.IStudyStrategy;
import learneverything.learning_service.domain.services.study_strategies.StrategyType;
import learneverything.learning_service.domain.services.study_strategies.question_strategy.QuestionStrategy;
import learneverything.learning_service.utils.StudyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Lấy ra những flashcard chưa học thuộc để hiện thị
 * Nếu flashcard nào có progress lớn hơn 3 là đã học thuộc
 */

@Service
@RequiredArgsConstructor
public class FlashcardSimpleStrategy implements IStudyStrategy {
    private final LessonService lessonService;
    private final LearningProgressRepository learningProgressRepository;

    @Override
    public List<Object> learn(Integer lessonId, String userId) {
        LessonDTO lesson = lessonService.get(lessonId);
        if (!lesson.getLearningType().equals(LearningType.FLASHCARD.getName())){
            throw new BaseException(Error.INVALID_STRATEGY,String.valueOf(lessonId));
        }
        List<Long> learningIds = lesson.getListLearning()
                .stream()
                .map(LearningDTO::getId)
                .toList();

        Map<Long, LearningProgressEntity> learningProgressMap = learningProgressRepository.findByLearningIdIn(learningIds)
                .stream()
                .collect(Collectors.toMap(LearningProgressEntity::getLearningId, e->e));

        List<LearningDTO> learningDTOS = new ArrayList<>();
        for (LearningDTO learning : lesson.getListLearning()){
            if (learningProgressMap.containsKey(learning.getId())){
                LearningProgressEntity learningProgressEntity = learningProgressMap.get(learning.getId());
                FlashCardDTO flashCard = (FlashCardDTO) learning;
                flashCard.setIsLearned(learningProgressEntity.getProgress() >= 3);
                learningDTOS.add(learning);
            }else {
                FlashCardDTO flashCard = (FlashCardDTO) learning;
                flashCard.setIsLearned(false);
                learningDTOS.add(learning);
            }
        }

        learningDTOS.sort((l1, l2) -> {
            FlashCardDTO flashCard1 = (FlashCardDTO) l1;
            FlashCardDTO flashCard2 = (FlashCardDTO) l2;
            if (flashCard1.getIsLearned() && !flashCard2.getIsLearned()) {
                return 1;
            } else if (!flashCard1.getIsLearned() && flashCard2.getIsLearned()) {
                return -1;
            }
            return 0;
        });
        return Collections.singletonList(learningDTOS);
    }

    @Override
    public Object evaluate(LessonResultDTO result, String userId) {
        LessonDTO lesson = lessonService.get(result.getLessonId());
        String learningType = lesson.getLearningType();
        StudyUtils.validateStudyStrategy(learningType,lesson.getId(), this.getClass());

        List<Long> learningIds = lesson.getListLearning()
                .stream()
                .map(LearningDTO::getId)
                .toList();
        Map<Long, LearningProgressEntity> learningProgressMap = learningProgressRepository.findByLearningIdIn(learningIds)
                .stream()
                .collect(Collectors.toMap(LearningProgressEntity::getLearningId, e->e));
        List<LearningProgressEntity> learningProgressEntities = new ArrayList<>();

        for (LearningResultDTO learningResult : result.getLearningResults()){
            if (learningResult.getClass() != FlashcardResultDTO.class) {
                throw new BaseException(Error.INVALID_LEARNING_TYPE, learningType);
            }
            FlashcardResultDTO flashcardResult = (FlashcardResultDTO) learningResult;

            LearningProgressEntity learningProgressEntity;
            if (learningProgressMap.containsKey(flashcardResult.getId())) {
                learningProgressEntity = learningProgressMap.get(flashcardResult.getId());
                if (flashcardResult.isMemorized()) {
                    learningProgressEntity.setProgress(3);
                } else {
                    learningProgressEntity.setProgress(0);
                }
            } else {
                learningProgressEntity = new LearningProgressEntity();
                learningProgressEntity.setLearningId(flashcardResult.getId());
                learningProgressEntity.setUserId(userId);
                if (flashcardResult.isMemorized()) {
                    learningProgressEntity.setProgress(3);
                } else {
                    learningProgressEntity.setProgress(0);
                }
            }
            learningProgressEntities.add(learningProgressEntity);
        }

        return learningProgressRepository.saveAll(learningProgressEntities);
    }
}
