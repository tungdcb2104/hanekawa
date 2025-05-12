package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.database.entities.learning_entities.flashcard.FlashcardEntity;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.learning.flashcard.FlashCardDTO;

public class FlashCardMapper implements ILearningMapper {
    @Override
    public LearningDTO entityToDto(LearningEntity entity) {
        return null;
    }

    @Override
    public LearningEntity dtoToEntity(LearningDTO dto) {
        return null;
    }
}
