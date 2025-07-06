package learneverything.learning_service.database.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "learning_history")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LearningHistoryEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "user_id")
    String userId;

    @Column(name = "learning_id")
    Long learningId;

    @Column(name = "lesson_id")
    Integer lessonId;

    /**
     * 0 : Sai
     * 1 : Dung
     * 2 : Không chọn
     */
    @Column(name = "status")
    Integer status;
}
