package learneverything.learning_service.application.controllers;

import jakarta.validation.Valid;
import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.CreateClazzRequestDTO;
import learneverything.learning_service.domain.dtos.clazz.SearchClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.UpdateClazzRequestDTO;
import learneverything.learning_service.domain.services.ClazzService;
import learneverything.learning_service.domain.services.EnrollmentService;
import learneverything.learning_service.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clazz")
@RequiredArgsConstructor
public class ClazzController {
    private final ClazzService clazzService;
    private final EnrollmentService enrollmentService;

    @PostMapping("")
    public ResponseEntity<Object> create(
            @RequestBody @Valid CreateClazzRequestDTO createClazzRequest
    ){
        return ResponseEntity.ok(clazzService.create(createClazzRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClazzDTO> get(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(clazzService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(clazzService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Integer id,
            @RequestBody @Valid UpdateClazzRequestDTO updateClazzRequest
    ){
        return ResponseEntity.ok(clazzService.update(id,updateClazzRequest));
    }

    @GetMapping("/learning-classes")
    public ResponseEntity<Object> getLearningClasses(){
        return ResponseEntity.ok(clazzService.getLearningClasses());
    }

    @GetMapping("")
    public ResponseEntity<Object> searchClasses(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "categories",required = false) String categories
    ){
        List<String> listCategory = null;
        if (!CommonUtils.isNullOrEmpty(categories)){
            listCategory = List.of(categories.split(","));
        }
        SearchClazzDTO searchClazzDTO = SearchClazzDTO.builder()
                .name(name)
                .categories(listCategory)
                .build();
        return ResponseEntity.ok(clazzService.searchClasses(searchClazzDTO));
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<Object> enrollClazz(
            @PathVariable("id") Integer clazzId
    ){
        return ResponseEntity.ok(enrollmentService.register(clazzId));
    }

    @PostMapping("/{id}/unregister")
    public ResponseEntity<Object> unregisterClazz(
            @PathVariable("id") Integer clazzId
    ){
        return ResponseEntity.ok(enrollmentService.unregister(clazzId));
    }
}
