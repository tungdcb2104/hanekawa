package learneverything.learning_service.domain.dtos.chapter;

import jakarta.validation.constraints.NotNull;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChapterRequestDTO {
    @NotNull
    String title;
    String description;
    Integer clazzId;
    String image;

    List<LessonDTO> listLesson;
}
