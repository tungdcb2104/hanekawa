package learneverything.learning_service.application.controllers;

import learneverything.learning_service.domain.services.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/study")
@RequiredArgsConstructor
public class StudyController {
    private final StudyService studyService;

    @GetMapping
    public ResponseEntity<Object> study(
            @RequestParam("lid")Integer lessonId,
            @RequestParam("sid")Integer strategyId
    ){
        String userId = "test_user_1";

        return ResponseEntity.ok(studyService.study(lessonId,userId,strategyId));
    }

    @PostMapping
    public ResponseEntity<Object> evaluate(
            @RequestBody Object assessment
    ){
        String userId = "test_user_1";

        return ResponseEntity.ok(studyService.evaluate(assessment,userId));
    }
}
