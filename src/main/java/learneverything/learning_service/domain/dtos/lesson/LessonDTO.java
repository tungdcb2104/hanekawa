package learneverything.learning_service.domain.dtos.lesson;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    String title;
    Integer chapterId;
    String description;
    @NotNull
    String learningType;
    List<LearningDTO> listLearning;
    int rate;
}
