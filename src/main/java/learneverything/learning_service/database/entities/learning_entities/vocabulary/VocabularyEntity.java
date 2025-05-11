package learneverything.learning_service.database.entities.learning_entities.vocabulary;

import jakarta.persistence.*;
import learneverything.learning_service.database.entities.LearningEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "vocabulary")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyEntity extends LearningEntity {
    @Column(nullable = false)
    String word;

    @Column(nullable = false)
    String meaning;

    String description;

    @Column(name = "image")
    String image;
}
