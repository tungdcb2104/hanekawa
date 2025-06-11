package learneverything.learning_service.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Data
public class BaseResponse<T> {
    private int status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorDes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    public BaseResponse( int status, T data){
        this.status = status;
        this.data = data;
    }
    public BaseResponse( int status, String errorDes){
        this.status = status;
        this.errorDes = errorDes;
    }
    public BaseResponse(){
        this.status = HttpStatus.OK.value();
    }

}
