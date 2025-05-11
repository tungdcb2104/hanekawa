package learneverything.learning_service.database.entities;

import jakarta.persistence.*;
import learneverything.learning_service.database.entities.learning_entities.LearningType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "lesson")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "chapter_id")
    Integer chapterId;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "learning_type")
    @Enumerated(EnumType.STRING)
    LearningType learningType;
}
