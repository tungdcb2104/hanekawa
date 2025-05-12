package learneverything.learning_service.domain.dtos.lesson;

import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonDTO {
    Integer id;
    String title;
    Integer chapterId;
    String description;
    String learningType;
    List<LearningDTO> listLearning;
}
