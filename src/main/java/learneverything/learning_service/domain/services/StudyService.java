package learneverything.learning_service.domain.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudyService {
    List<Object> study(Integer id,Class strategy);
    Object evaluate(Object result);
}
