package learneverything.learning_service.database.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class LearningEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "learning_seq_gen")
    @SequenceGenerator(name = "learning_seq_gen",sequenceName = "learning_id_seq",allocationSize = 1)
    Long id;

    @Column(name = "lesson_id")
    Integer lessonId;

    @Column
    String type;
}
