package learneverything.learning_service.domain.dtos.learning;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import learneverything.learning_service.domain.dtos.learning.flashcard.FlashCardDTO;
import learneverything.learning_service.domain.dtos.learning.question.QuestionDTO;
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
})
public abstract class LearningDTO {
    Long id;
    String type;
}
