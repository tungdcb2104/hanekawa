package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.database.entities.LearningHistoryEntity;
import learneverything.learning_service.database.repositories.LearningHistoryRepository;
import learneverything.learning_service.database.repositories.LessonRepository;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.learning_history.LearningHistoryDTO;
import learneverything.learning_service.domain.dtos.learning_history.LessonResultDTO;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.services.LearningHistoryService;
import learneverything.learning_service.domain.services.LessonService;
import learneverything.learning_service.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LearningHistoryServiceImpl implements LearningHistoryService {
    private final LessonService lessonService;
    private final LearningHistoryRepository learningHistoryRepository;

    @Override
    public LessonResultDTO submit(LessonResultDTO result) {
        validateLessonResult(result);
        String userId = CommonUtils.getUserId();

        List<LearningHistoryEntity> learningHistoryEntityList = new ArrayList<>();
        for (LearningHistoryDTO learningHistoryDTO : result.getLearningHistories()){
            LearningHistoryEntity learningHistoryEntity = new LearningHistoryEntity();
            learningHistoryEntity.setLearningId(learningHistoryDTO.getLearningId());
            learningHistoryEntity.setStatus(learningHistoryDTO.getStatus());
            learningHistoryEntity.setUserId(userId);
            learningHistoryEntity.setLessonId(result.getLessonId());


            learningHistoryEntityList.add(learningHistoryEntity);
        }

        learningHistoryRepository.saveAll(learningHistoryEntityList);
        return result;
    }

    private void validateLessonResult(LessonResultDTO result){
        LessonDTO lesson = lessonService.get(result.getLessonId());
        Set<Long> learningIdSet = lesson.getListLearning()
                .stream()
                .map(LearningDTO::getId)
                .collect(Collectors.toSet());

        for (LearningHistoryDTO learningHistoryDTO : result.getLearningHistories()){
            if (!learningIdSet.contains(learningHistoryDTO.getLearningId())){
                throw new BaseException(Error.BAD_REQUEST);
            }

            if (learningHistoryDTO.getStatus() > 2 || learningHistoryDTO.getStatus() < 0){
                throw new BaseException(Error.BAD_REQUEST);
            }
        }
    }

}
