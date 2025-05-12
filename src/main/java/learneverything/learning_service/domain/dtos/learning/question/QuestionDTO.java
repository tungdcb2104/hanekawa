package learneverything.learning_service.domain.dtos.learning.question;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "questionType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleChoiceQuestionDTO.class, name = "single_choice_question"),
        @JsonSubTypes.Type(value = MultiChoiceQuestionDTO.class, name = "multi_choice_question"),
        @JsonSubTypes.Type(value = FillQuestionDTO.class,name = "fill_question")
})
public class QuestionDTO extends LearningDTO {
    String question;
    String description;
    String questionType;
}
