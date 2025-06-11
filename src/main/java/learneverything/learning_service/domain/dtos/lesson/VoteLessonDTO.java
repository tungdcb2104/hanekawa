package learneverything.learning_service.domain.dtos.lesson;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteLessonDTO {
    @NotBlank
    private Integer lessonId;
    private Integer rate;
}
