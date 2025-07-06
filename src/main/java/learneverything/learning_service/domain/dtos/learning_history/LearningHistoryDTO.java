package learneverything.learning_service.domain.dtos.learning_history;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LearningHistoryDTO {
    @NotNull
    Long learningId;

    @NotNull
    Integer status; // 0 : Sai ,1 : Dung
}
