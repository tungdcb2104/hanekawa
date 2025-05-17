package learneverything.learning_service.application.exceptions;

import lombok.Getter;

@Getter
public enum Error {
    NOT_FOUND_LESSON(404,"Not found lesson %s"),
    NOT_FOUND_CHAPTER(404,"Not found chapter %s"),
    BAD_REQUEST(400,"Bad request"),
    INVALID_LESSON(400,"Invalid lesson"),
    PAYLOAD_TOO_LARGE(413,"Payload too large"),
    INVALID_FILE(422,"Invalid file name"),
    INVALID_STRATEGY(400,"Strategy %s is invalid for lesson %s"),
    NOT_FOUND_STRATEGY(400,"Not found strategy"),
    UNAVAILABLE_STRATEGY(400,"Strategy is unavailable now")
    ;

    Error(int statusCode,String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    private final String message;
    private final int statusCode;
}
