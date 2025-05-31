package learneverything.learning_service.application.exceptions;

import lombok.Getter;

@Getter
public enum Error {
    BAD_REQUEST(400,"Bad request"),

    NOT_FOUND_CLAZZ(404,"Not found clazz %s"),
    NOT_FOUND_LESSON(404,"Not found lesson %s"),
    NOT_FOUND_CHAPTER(404,"Not found chapter %s"),
    NOT_FOUND_STRATEGY(400,"Not found strategy"),

    PAYLOAD_TOO_LARGE(413,"Payload too large"),

    UNAVAILABLE_STRATEGY(400,"Strategy is unavailable now"),

    INVALID_LEARNING_TYPE(400,"%s is invalid learning type"),
    INVALID_FILE(422,"Invalid file name"),
    INVALID_LESSON(400,"Invalid lesson"),
    INVALID_STRATEGY(400,"Strategy is invalid for lesson %s"),
    ;

    Error(int statusCode,String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    private final String message;
    private final int statusCode;
}
