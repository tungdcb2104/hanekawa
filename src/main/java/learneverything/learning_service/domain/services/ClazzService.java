package learneverything.learning_service.domain.services;

import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.CreateClazzRequestDTO;
import learneverything.learning_service.domain.dtos.clazz.UpdateClazzRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ClazzService {
    ClazzDTO get(Integer id);
    Object create(CreateClazzRequestDTO createClazzRequest);
    String delete(Integer id);
    Object update(Integer id,UpdateClazzRequestDTO updateClazzRequest);
}
