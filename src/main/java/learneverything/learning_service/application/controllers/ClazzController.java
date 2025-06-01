package learneverything.learning_service.application.controllers;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.CreateClazzRequestDTO;
import learneverything.learning_service.domain.dtos.clazz.SearchClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.UpdateClazzRequestDTO;
import learneverything.learning_service.domain.services.ClazzService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clazz")
@RequiredArgsConstructor
public class ClazzController {
    private final ClazzService clazzService;

    @PostMapping("")
    public ResponseEntity<Object> create(
            @RequestBody CreateClazzRequestDTO createClazzRequest
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
            @RequestBody UpdateClazzRequestDTO updateClazzRequest
    ){
        return ResponseEntity.ok(clazzService.update(id,updateClazzRequest));
    }

    @GetMapping("/learning-classes")
    public ResponseEntity<Object> getLearningClasses(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "categories",required = false) String categories
    ){
        List<String> listCategory = List.of(categories.split(","));
        SearchClazzDTO searchClazzDTO = SearchClazzDTO.builder()
                .name(name)
                .categories(listCategory)
                .build();
        return ResponseEntity.ok(clazzService.getLearningClasses(searchClazzDTO));
    }
}
