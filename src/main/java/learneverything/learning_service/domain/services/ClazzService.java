package learneverything.learning_service.domain.services;

import learneverything.learning_service.domain.dtos.BaseResponse;
import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.CreateClazzRequestDTO;
import learneverything.learning_service.domain.dtos.clazz.SearchClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.UpdateClazzRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClazzService {
    ClazzDTO get(Integer id);
    Object create(CreateClazzRequestDTO createClazzRequest);
    String delete(Integer id);
    Object update(Integer id,UpdateClazzRequestDTO updateClazzRequest);
    List<ClazzDTO> getLearningClasses();
    List<ClazzDTO> searchClasses(SearchClazzDTO searchClazzDTO);

    BaseResponse<Void> pin(Integer clazzId);
    BaseResponse<Void> unPin(Integer clazzId);
}
