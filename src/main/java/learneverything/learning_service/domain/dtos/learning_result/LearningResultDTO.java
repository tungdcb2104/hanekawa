package learneverything.learning_service.domain.dtos.learning_result;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuestionResultDTO.class,name = "question"),
        @JsonSubTypes.Type(value = FlashcardResultDTO.class,name = "flashcard"),
        @JsonSubTypes.Type(value = FlashcardProgressResultDTO.class,name = "flashcard_progress")
})
public abstract class LearningResultDTO {
    Long id;
}
