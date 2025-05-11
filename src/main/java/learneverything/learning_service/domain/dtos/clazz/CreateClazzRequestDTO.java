package learneverything.learning_service.domain.dtos.clazz;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateClazzRequestDTO {
    private String title;
    private String description;
}
