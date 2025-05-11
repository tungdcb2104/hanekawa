package learneverything.learning_service.database.entities;

import jakarta.persistence.*;
import learneverything.learning_service.database.entities.learning_entities.LearningType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class LearningEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "learning_seq_gen")
    @SequenceGenerator(name = "learning_seq_gen",sequenceName = "learning_id_seq",allocationSize = 1)
    Long id;

    @Column(name = "lesson_id")
    Integer lessonId;

    @Column
    @Enumerated(EnumType.STRING)
    LearningType type;
}
