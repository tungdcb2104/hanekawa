package learneverything.learning_service.application.exceptions;

import lombok.Getter;

@Getter
public enum Error {
    NOT_FOUND_LESSON(404,"Not found lesson %s"),
    BAD_REQUEST(400,"Bad request"),
    INVALID_LESSON(400,"Invalid lesson"),
    ;

    Error(int statusCode,String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    private final String message;
    private final int statusCode;
}
