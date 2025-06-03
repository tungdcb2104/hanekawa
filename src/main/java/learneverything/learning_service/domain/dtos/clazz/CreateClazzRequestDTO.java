package learneverything.learning_service.domain.dtos.clazz;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateClazzRequestDTO {
    @NotNull
    private String title;
    private String description;
}
