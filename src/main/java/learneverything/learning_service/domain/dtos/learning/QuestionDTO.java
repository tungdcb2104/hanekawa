package learneverything.learning_service.domain.dtos.learning;


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
    String questionType;
}
