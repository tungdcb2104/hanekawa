package learneverything.learning_service.application.controllers;

import learneverything.learning_service.database.entities.learning_entities.vocabulary.VocabularyEntity;
import learneverything.learning_service.database.repositories.VocabularyRepository;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
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

    @PostMapping("/vocab")
    public String createVocab(@RequestBody VocabularyEntity vocabularyEntity){
        learningRepository.save(vocabularyEntity);
        return "SUCCESS!";
    }

    @PostMapping("")
    public String create(@RequestBody LessonDTO lessonDTO){

    }
}
