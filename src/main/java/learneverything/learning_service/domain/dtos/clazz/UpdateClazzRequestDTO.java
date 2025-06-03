package learneverything.learning_service.domain.dtos.clazz;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateClazzRequestDTO {
    @NotNull
    private String title;
    private String description;
}
