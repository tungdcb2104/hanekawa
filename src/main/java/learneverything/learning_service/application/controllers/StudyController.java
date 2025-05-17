package learneverything.learning_service.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/study")
@RequiredArgsConstructor
public class StudyController {
    @GetMapping
    public ResponseEntity<Object> study(
            @RequestParam("lid")Integer lessonId,
            @RequestParam("sid")Integer strategyId
    ){
        Long userId = 1L;

        return null;
    }

    @PostMapping
    public ResponseEntity<Object> evaluate(
            @RequestBody Object assessment
    ){
        Long userId = 1L;

        return null;
    }
}
