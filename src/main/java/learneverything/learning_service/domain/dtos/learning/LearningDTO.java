package learneverything.learning_service.domain.dtos.learning;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import learneverything.learning_service.domain.dtos.learning.flashcard.FlashCardDTO;
import learneverything.learning_service.domain.dtos.learning.question.FillQuestionDTO;
import learneverything.learning_service.domain.dtos.learning.question.MultiChoiceQuestionDTO;
import learneverything.learning_service.domain.dtos.learning.question.QuestionDTO;
import learneverything.learning_service.domain.dtos.learning.question.SingleChoiceQuestionDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuestionDTO.class, name = "question"),
        @JsonSubTypes.Type(value = FlashCardDTO.class, name = "flashcard"),
        @JsonSubTypes.Type(value = SingleChoiceQuestionDTO.class, name = "single_choice_question"),
        @JsonSubTypes.Type(value = MultiChoiceQuestionDTO.class, name = "multi_choice_question"),
        @JsonSubTypes.Type(value = FillQuestionDTO.class,name = "fill_question")
})
public abstract class LearningDTO {
    Long id;
    String type;
}
