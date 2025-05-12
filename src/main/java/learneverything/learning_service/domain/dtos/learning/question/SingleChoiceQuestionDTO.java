package learneverything.learning_service.domain.dtos.learning.question;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SingleChoiceQuestionDTO extends QuestionDTO{
    List<String> choices;
    Integer correctChoice;
}
