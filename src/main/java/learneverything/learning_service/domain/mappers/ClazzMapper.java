package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.ClazzEntity;
import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.CreateClazzRequestDTO;
import learneverything.learning_service.domain.dtos.clazz.UpdateClazzRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ClazzMapper {
    ClazzDTO toDTO(ClazzEntity clazzEntity);

    ClazzEntity toEntity(ClazzDTO clazzDTO);

    ClazzEntity createDTOToEntity(CreateClazzRequestDTO createClazzRequest);

    ClazzEntity updateDTOToEntity(UpdateClazzRequestDTO updateClazzRequest);
}
