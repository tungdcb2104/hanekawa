package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.database.entities.learning_entities.vocabulary.VocabularyEntity;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.learning.vocabulary.VocabularyDTO;
import learneverything.learning_service.utils.CommonUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VocabularyMapper implements ILearningMapper {
    @Override
    public LearningDTO entityToDto(LearningEntity entity) {
        return null;
    }

    @Override
    public LearningEntity dtoToEntity(LearningDTO dto) {
        VocabularyDTO vocabularyDTO = (VocabularyDTO) dto;

        VocabularyEntity vocabulary = new VocabularyEntity();
        vocabulary.setWord(vocabularyDTO.getWord());
        vocabulary.setMeaning(vocabularyDTO.getMeaning());
        vocabulary.setDescription(vocabularyDTO.getDescription());
        vocabulary.setType("vocabulary");
        vocabulary.setImage(vocabularyDTO.getImage());

        return vocabulary;
    }
}
