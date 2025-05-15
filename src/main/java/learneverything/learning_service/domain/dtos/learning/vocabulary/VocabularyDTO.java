package learneverything.learning_service.domain.dtos.learning.vocabulary;

import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyDTO extends LearningDTO {
    String word;
    String meaning;
    String description;
    String image;
}
