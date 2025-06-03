package learneverything.learning_service.application.controllers;

import jakarta.validation.Valid;
import learneverything.learning_service.domain.dtos.chapter.ChapterDTO;
import learneverything.learning_service.domain.dtos.chapter.CreateChapterRequestDTO;
import learneverything.learning_service.domain.dtos.chapter.UpdateChapterRequestDTO;
import learneverything.learning_service.domain.services.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/chapter")
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;

    @PostMapping("")
    public ResponseEntity<Object> createChapter(@RequestBody @Valid CreateChapterRequestDTO createChapterRequest) {
        return ResponseEntity.ok(chapterService.create(createChapterRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDTO> get(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(chapterService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(chapterService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody @Valid UpdateChapterRequestDTO updateChapterRequest) {
        return ResponseEntity.ok(chapterService.update(id, updateChapterRequest));
    }
}
