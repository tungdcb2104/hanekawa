package learneverything.learning_service.database.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "enrollment")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "lesson_id")
    private Long lessonId;
    @Column(name = "status")
    private Integer status;
}
