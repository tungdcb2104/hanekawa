package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.ClazzEntity;
import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.CreateClazzRequestDTO;
import learneverything.learning_service.domain.dtos.clazz.UpdateClazzRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ClazzMapper {
    ClazzDTO toDTO(ClazzEntity clazzEntity);

    ClazzEntity toEntity(ClazzDTO clazzDTO);

    ClazzEntity createDTOToEntity(CreateClazzRequestDTO createClazzRequest);

    void dtoToEntity(ClazzDTO clazzDTO,@MappingTarget ClazzEntity clazzEntity);
    void entityToDto(ClazzEntity clazzEntity,@MappingTarget ClazzDTO clazzDTO);
    void updateDtoToEntity(UpdateClazzRequestDTO updateClazzRequestDTO,@MappingTarget ClazzEntity clazz);
}
