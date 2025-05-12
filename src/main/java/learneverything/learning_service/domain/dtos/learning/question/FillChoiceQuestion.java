package learneverything.learning_service.domain.dtos.learning.question;

import learneverything.learning_service.database.entities.learning_entities.question.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillChoiceQuestion extends QuestionDTO{
    String answer;

    @Override
    public FillChoiceQuestion entityToDTO(QuestionEntity question) {
        FillChoiceQuestion questionDTO = new FillChoiceQuestion();
        questionDTO.setQuestion(question.getQuestion());
        questionDTO.setQuestionType(question.getQuestionType().getName());
        questionDTO.setDescription(question.getDescription());
        questionDTO.setAnswer((String) question.getAnswer().get("answer"));

        return questionDTO;
    }

    @Override
    public QuestionEntity dtoToEntity(QuestionDTO question) {
        return null;
    }
}
