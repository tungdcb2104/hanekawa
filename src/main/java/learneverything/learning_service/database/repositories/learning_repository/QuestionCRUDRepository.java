package learneverything.learning_service.database.repositories.learning_repository;

import learneverything.learning_service.database.entities.learning_entities.question.QuestionEntity;
import learneverything.learning_service.database.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionCRUDRepository implements CRUDLearningRepository<QuestionEntity> {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public QuestionEntity getLearning(Long id) {
        return null;
//        return questionRepository.getReferenceById(id);
    }

    @Override
    public List<QuestionEntity> getLearningByLesson(Integer lessonId) {
//        return questionRepository.getQuestionsOfLesson(lessonId);
        return null;
    }

    @Override
    public QuestionEntity saveLearning(QuestionEntity learning) {
//        return questionRepository.save(learning);
        return null;
    }

    @Override
    public List<QuestionEntity> saveLearning(List<QuestionEntity> learnings) {
//        return questionRepository.saveAll(learnings);
        return null;
    }

    @Override
    public void deleteLearning(QuestionEntity learning) {
//        questionRepository.delete(learning);
        return;
    }

    @Override
    public void deleteLearning(List<QuestionEntity> learnings) {
//        questionRepository.deleteAll(learnings);
        return;
    }
}
