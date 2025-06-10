package learneverything.learning_service.domain.dtos.learning.flashcard;

import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FlashCardDTO extends LearningDTO {
    String frontContent;
    String backContent;
    String frontImage;
    String backImage;
    Boolean isLearned;
}
