package learneverything.learning_service.domain.dtos.learning_result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonResultDTO {
    Integer lessonId;
    Integer strategyId;
    List<LearningResultDTO> learningResults;
}
