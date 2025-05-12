package learneverything.learning_service.domain.dtos.learning.question;

import learneverything.learning_service.database.entities.learning_entities.question.QuestionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SingleChoiceQuestion extends QuestionDTO{
    List<String> choices;
    Integer correctChoice;

    @Override
    public QuestionDTO entityToDTO(QuestionEntity question) {
        return null;
    }

    @Override
    public QuestionEntity dtoToEntity(QuestionDTO question) {
        return null;
    }
}
