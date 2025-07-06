package learneverything.learning_service.domain.dtos.learning_history;

import lombok.Data;

import java.util.List;

@Data
public class LessonResultDTO {
    Integer lessonId;
    List<LearningHistoryDTO> learningHistories;
}
