package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.database.entities.learning_entities.question.QuestionEntity;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.dtos.learning.question.FillQuestionDTO;
import learneverything.learning_service.domain.dtos.learning.question.MultiChoiceQuestionDTO;
import learneverything.learning_service.domain.dtos.learning.question.QuestionDTO;
import learneverything.learning_service.domain.dtos.learning.question.SingleChoiceQuestionDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionMapper implements ILearningMapper {
    @Override
    public QuestionDTO entityToDto(LearningEntity entity) {
        QuestionEntity questionEntity = (QuestionEntity) entity;

        QuestionDTO questionDTO;
        switch (questionEntity.getQuestionType()){
            case FILL:
                questionDTO = new FillQuestionDTO();
                questionDTO.setQuestionType(QuestionEntity.QuestionType.FILL.getName());
                ((FillQuestionDTO)questionDTO).setAnswer((String) questionEntity.getAnswer().get("answer"));
                break;
            case MULTI_CHOICE:
                questionDTO = new MultiChoiceQuestionDTO();
                questionDTO.setQuestionType(QuestionEntity.QuestionType.MULTI_CHOICE.getName());
                ((MultiChoiceQuestionDTO) questionDTO).setChoices((Map<String, Boolean>) questionEntity.getAnswer().get("choices"));
                break;
            case SINGLE_CHOICE:
                questionDTO = new SingleChoiceQuestionDTO();
                questionDTO.setQuestionType(QuestionEntity.QuestionType.SINGLE_CHOICE.getName());
                ((SingleChoiceQuestionDTO)questionDTO).setChoices((List<String>) questionEntity.getAnswer().get("choices"));
                ((SingleChoiceQuestionDTO)questionDTO).setCorrectChoice((Integer) questionEntity.getAnswer().get("correctChoice"));
                break;
            default:
                return null;
        }
        questionDTO.setId(questionEntity.getId());
        questionDTO.setQuestion(questionEntity.getQuestion());
        questionDTO.setDescription(questionEntity.getDescription());
        questionDTO.setType("question");
        return questionDTO;
    }

    @Override
    public QuestionEntity dtoToEntity(LearningDTO dto) {
        QuestionDTO questionDTO = (QuestionDTO) dto;

        QuestionEntity question = new QuestionEntity();
        question.setQuestion(questionDTO.getQuestion());
        question.setType("question");
        question.setDescription(questionDTO.getDescription());

        Map<String,Object> answer = new HashMap<>();
        switch (questionDTO.getQuestionType()){
            case "single_choice_question" :
                SingleChoiceQuestionDTO singleChoiceQuestionDTO = (SingleChoiceQuestionDTO) questionDTO;
                question.setQuestionType(QuestionEntity.QuestionType.SINGLE_CHOICE);
                answer.put("choices",singleChoiceQuestionDTO.getChoices());
                answer.put("correctChoice",singleChoiceQuestionDTO.getCorrectChoice());
                break;
            case "multi_choice_question" :
                MultiChoiceQuestionDTO multiChoiceQuestionDTO = (MultiChoiceQuestionDTO) questionDTO;
                question.setQuestionType(QuestionEntity.QuestionType.MULTI_CHOICE);
                answer.put("choices",multiChoiceQuestionDTO.getChoices());
                break;
            case "fill_question" :
                FillQuestionDTO fillQuestionDTO = (FillQuestionDTO) questionDTO;
                question.setQuestionType(QuestionEntity.QuestionType.FILL);
                answer.put("answer",fillQuestionDTO.getAnswer());
                break;
        }

        question.setAnswer(answer);
        return question;
    }
}
