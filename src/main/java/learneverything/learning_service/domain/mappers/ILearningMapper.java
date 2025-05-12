package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;

public interface ILearningMapper {
    LearningDTO entityToDto(LearningEntity entity);
    LearningEntity dtoToEntity(LearningDTO dto);
}
