package learneverything.learning_service.application.controllers;

import jakarta.validation.Valid;
import learneverything.learning_service.database.repositories.VocabularyRepository;
import learneverything.learning_service.domain.dtos.learning_history.LessonResultDTO;
import learneverything.learning_service.domain.dtos.lesson.LessonDTO;
import learneverything.learning_service.domain.dtos.lesson.VoteLessonDTO;
import learneverything.learning_service.domain.services.LearningHistoryService;
import learneverything.learning_service.domain.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final LearningHistoryService learningHistoryService;

    @PostMapping("")
    public ResponseEntity<LessonDTO> create(@RequestBody @Valid LessonDTO lessonDTO){
        return ResponseEntity.ok(lessonService.create(lessonDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> get(@PathVariable Integer id){
        return ResponseEntity.ok(lessonService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDTO> update(@PathVariable Integer id, @RequestBody @Valid LessonDTO lessonDTO){
        lessonDTO.setId(id);

        return ResponseEntity.ok(lessonService.update(lessonDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        return ResponseEntity.ok(lessonService.delete(id));
    }

    @PostMapping("/vote")
    public ResponseEntity<Object> voteLesson(@RequestBody @Valid VoteLessonDTO request){
        return ResponseEntity.ok(lessonService.voteLesson(request));
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<Object> submit(@PathVariable Integer id,@RequestBody @Valid LessonResultDTO lessonResult){
        lessonResult.setLessonId(id);
        return ResponseEntity.ok(learningHistoryService.submit(lessonResult));
    }
}
