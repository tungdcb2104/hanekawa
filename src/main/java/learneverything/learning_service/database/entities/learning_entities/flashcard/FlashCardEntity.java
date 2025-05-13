package learneverything.learning_service.database.entities.learning_entities.flashcard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import learneverything.learning_service.database.entities.LearningEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "flashcard")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlashCardEntity extends LearningEntity {
    @Column(name = "front_content", nullable = false)
    String frontContent;
    @Column(name = "back_content", nullable = false)
    String backContent;
    @Column(name = "front_image")
    String frontImage;
    @Column(name = "back_image")
    String backImage;

    @Column(name = "front_extra")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> frontExtra;

    @Column(name = "back_extra")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> backExtra;
}
