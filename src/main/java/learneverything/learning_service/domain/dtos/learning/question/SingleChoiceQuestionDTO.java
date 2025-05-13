package learneverything.learning_service.domain.dtos.learning.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleChoiceQuestionDTO extends QuestionDTO{
    List<String> choices;
    Integer correctChoice;
}
