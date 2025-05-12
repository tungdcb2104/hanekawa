package learneverything.learning_service.domain.dtos.learning.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillQuestionDTO extends QuestionDTO{
    String answer;
}
