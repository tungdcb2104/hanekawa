package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.ChapterEntity;
import learneverything.learning_service.domain.dtos.chapter.ChapterDTO;
import learneverything.learning_service.domain.dtos.chapter.CreateChapterRequestDTO;
import learneverything.learning_service.domain.dtos.chapter.UpdateChapterRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
     ChapterDTO toDTO(ChapterEntity chapterEntity);

     ChapterEntity toEntity(ChapterDTO chapterDTO);

     ChapterEntity createDTOToEntity(CreateChapterRequestDTO createChapterRequest);

     ChapterEntity updateDTOToEntity(UpdateChapterRequestDTO updateChapterRequest);
}
