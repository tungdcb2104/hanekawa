package learneverything.learning_service.domain.dtos.learning.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiChoiceQuestionDTO extends QuestionDTO{
    Map<String,Boolean> choices;
}
