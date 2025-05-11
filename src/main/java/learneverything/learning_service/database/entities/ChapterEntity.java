package learneverything.learning_service.database.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "chapter")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChapterEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "clazz_id")
    Integer clazzId;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "image")
    String image;

    // 0 is inactive, 1 is active, 2 is draft
    @Column(name = "status")
    Integer status;
}
