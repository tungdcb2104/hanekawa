package learneverything.learning_service.application.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(value = BaseException.class)
    ResponseEntity<String> handleBaseException(BaseException exception){
        log.error("Exception : {}",String.format(exception.getError().getMessage(), (String[]) exception.getErrorTemplate()));
        return ResponseEntity.status(exception.getError().getStatusCode())
                .body(String.format(exception.getError().getMessage(), (String[]) exception.getErrorTemplate()));
    }
}
