package learneverything.learning_service.database.entities.learning_entities.question;

import jakarta.persistence.*;
import learneverything.learning_service.database.entities.LearningEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "question")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity extends LearningEntity {
    @Column(nullable = false)
    String question;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    QuestionType questionType;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> answer;
}


