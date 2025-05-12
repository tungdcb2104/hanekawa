package learneverything.learning_service.domain.dtos.learning.question;


import learneverything.learning_service.database.entities.learning_entities.question.QuestionEntity;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class QuestionDTO extends LearningDTO {
    String question;
    String description;
    String questionType;

    public abstract QuestionDTO entityToDTO(QuestionEntity question);
    public abstract QuestionEntity dtoToEntity(QuestionDTO question);
}
