package learneverything.learning_service.domain.dtos.clazz;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SearchClazzDTO {
    String name;
    List<String> categories;
}
