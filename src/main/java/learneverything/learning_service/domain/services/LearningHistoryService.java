package learneverything.learning_service.domain.services;

import learneverything.learning_service.domain.dtos.learning_history.LessonResultDTO;

public interface LearningHistoryService {
    LessonResultDTO submit(LessonResultDTO result);
}
