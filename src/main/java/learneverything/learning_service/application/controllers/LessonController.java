package learneverything.learning_service.application.controllers;

import learneverything.learning_service.database.entities.learning_entities.vocabulary.VocabularyEntity;
import learneverything.learning_service.database.repositories.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final VocabularyRepository learningRepository;

    @PostMapping("")
    public String create(@RequestBody VocabularyEntity vocabularyEntity){
        learningRepository.save(vocabularyEntity);
        return "SUCCESS!";
    }
}
