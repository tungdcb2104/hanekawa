package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.LessonEntity;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonDTO entityToDto(LessonEntity lesson);
    LessonEntity dtoToEntity(LessonDTO lessonDTO);
}
