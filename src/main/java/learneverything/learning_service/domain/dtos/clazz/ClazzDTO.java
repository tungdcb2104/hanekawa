package learneverything.learning_service.domain.dtos.clazz;

import learneverything.learning_service.domain.dtos.chapter.ChapterDTO;
import lombok.Data;

import java.util.List;

@Data
public class ClazzDTO {
    Integer id;
    String title;
    String description;
    String image;
    List<String> categories;
    List<ChapterDTO> listChapter;
    Boolean pinned;
}
