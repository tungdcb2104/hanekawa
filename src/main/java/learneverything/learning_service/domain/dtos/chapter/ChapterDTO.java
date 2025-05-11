package learneverything.learning_service.domain.dtos.chapter;

import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDTO {
    Integer id;
    String title;
    String description;
    String image;
    List<LessonDTO> listLesson;
}
