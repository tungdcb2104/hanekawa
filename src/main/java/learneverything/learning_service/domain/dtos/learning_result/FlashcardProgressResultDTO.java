package learneverything.learning_service.domain.dtos.learning_result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FlashcardProgressResultDTO extends LearningResultDTO{
    int progress;
}
