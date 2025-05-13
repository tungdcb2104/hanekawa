package learneverything.learning_service.domain.services.learning_repository.crud_learning;

import learneverything.learning_service.database.entities.learning_entities.flashcard.FlashCardEntity;
import learneverything.learning_service.database.repositories.FlashCardRepository;
import learneverything.learning_service.domain.services.learning_repository.ICRUDLearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CRUDFlashCardService implements ICRUDLearningService<FlashCardEntity> {
    @Autowired
    private FlashCardRepository flashCardRepository;

    @Override
    public FlashCardEntity getLearning(Long id) {
        return flashCardRepository.getReferenceById(id);
    }

    @Override
    public List<FlashCardEntity> getLearningByLesson(Integer lessonId) {
        return flashCardRepository.getFlashcardsByLesson(lessonId);
    }

    @Override
    public FlashCardEntity saveLearning(FlashCardEntity learning) {
        flashCardRepository.save(learning);

        return learning;
    }

    @Override
    public List<FlashCardEntity> saveLearning(List<FlashCardEntity> learning) {
        return flashCardRepository.saveAll(learning);
    }

    @Override
    public void deleteLearning(FlashCardEntity learning) {
        flashCardRepository.delete(learning);
    }

    @Override
    public void deleteLearning(List<FlashCardEntity> learnings) {
        flashCardRepository.deleteAll(learnings);
    }
}
