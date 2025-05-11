package learneverything.learning_service.database.entities.learning_entities.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionType {
    MULTI_CHOICE(1,"multi choice"),
    SINGLE_CHOICE(2,"single choice"),
    FILL(3,"fill");

    private final Integer id;
    private final String name;
}
