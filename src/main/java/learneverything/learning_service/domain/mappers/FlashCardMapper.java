package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.database.entities.learning_entities.flashcard.FlashCardEntity;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.learning.flashcard.FlashCardDTO;
import org.springframework.stereotype.Component;

@Component
public class FlashCardMapper implements ILearningMapper {
    @Override
    public LearningDTO entityToDto(LearningEntity entity) {
        FlashCardEntity flashcardEntity = (FlashCardEntity) entity;

        FlashCardDTO flashCardDTO = new FlashCardDTO();
        flashCardDTO.setFrontContent(flashcardEntity.getFrontContent());
        flashCardDTO.setBackContent(flashcardEntity.getBackContent());
//        flashCardDTO.setFrontImage(flashcardEntity.getFrontImage());
//        flashCardDTO.setBackImage(flashcardEntity.getBackImage());
//        flashCardDTO.setFrontExtra(flashcardEntity.getFrontExtra());
//        flashCardDTO.setBackExtra(flashcardEntity.getBackExtra());

        return flashCardDTO;
    }

    @Override
    public LearningEntity dtoToEntity(LearningDTO dto) {
        FlashCardDTO flashCardDTO = (FlashCardDTO) dto;

        FlashCardEntity flashcard = new FlashCardEntity();
        flashcard.setFrontContent(flashCardDTO.getFrontContent());
        flashcard.setBackContent(flashCardDTO.getBackContent());
        flashcard.setFrontImage(flashCardDTO.getFrontImage());
        flashcard.setBackImage(flashCardDTO.getBackImage());
        flashcard.setType("flashcard");

        return flashcard;
    }
}
