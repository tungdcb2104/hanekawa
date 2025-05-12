package learneverything.learning_service.domain.dtos.learning.question;

import learneverything.learning_service.database.entities.learning_entities.question.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiChoiceQuestion extends QuestionDTO{
    Map<String,Boolean> choices;

    @Override
    public MultiChoiceQuestion entityToDTO(QuestionEntity question) {
        MultiChoiceQuestion multiChoiceQuestion = new MultiChoiceQuestion();
        multiChoiceQuestion.setQuestion(question.getQuestion());
        multiChoiceQuestion.setDescription(question.getDescription());
        multiChoiceQuestion.setQuestionType(question.getQuestionType().getName());
        multiChoiceQuestion.setChoices((Map<String, Boolean>) question.getAnswer().get("choices"));

        return multiChoiceQuestion;
    }

    @Override
    public QuestionEntity dtoToEntity(QuestionDTO question) {
        return null;
    }
}
