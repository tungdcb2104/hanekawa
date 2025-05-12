package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.database.entities.learning_entities.question.QuestionEntity;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.learning.question.QuestionDTO;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper implements ILearningMapper {
    @Override
    public QuestionDTO entityToDto(LearningEntity entity) {
        QuestionEntity questionEntity = (QuestionEntity) entity;


        return null;
    }

    @Override
    public QuestionEntity dtoToEntity(LearningDTO dto) {
        QuestionDTO questionDTO = (QuestionDTO) dto;

        QuestionEntity question = new QuestionEntity();
        question.setQuestion(questionDTO.getQuestion());
        question.setQuestionType(QuestionEntity.QuestionType.MULTI_CHOICE);
        question.setType("question");
        question.setDescription(questionDTO.getDescription());

        return question;
    }
}
