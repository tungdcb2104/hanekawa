package learneverything.learning_service.domain.services.learning_repository.crud_learning;

import learneverything.learning_service.database.entities.learning_entities.vocabulary.VocabularyEntity;
import learneverything.learning_service.database.repositories.VocabularyRepository;
import learneverything.learning_service.domain.services.learning_repository.ICRUDLearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CRUDVocabularyService implements ICRUDLearningService<VocabularyEntity> {
    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Override
    public VocabularyEntity getLearning(Long id) {
        return vocabularyRepository.getReferenceById(id);
    }

    @Override
    public List<VocabularyEntity> getLearningByLesson(Integer lessonId) {
        return vocabularyRepository.getVocabularyByLesson(lessonId);
    }

    @Override
    public VocabularyEntity saveLearning(VocabularyEntity learning) {
        vocabularyRepository.save(learning);

        return learning;
    }

    @Override
    public List<VocabularyEntity> saveLearning(List<VocabularyEntity> learning) {
        return vocabularyRepository.saveAll(learning);
    }

    @Override
    public void deleteLearning(VocabularyEntity learning) {
        vocabularyRepository.delete(learning);
    }

    @Override
    public void deleteLearning(List<VocabularyEntity> learnings) {
        vocabularyRepository.deleteAll(learnings);
    }
}
