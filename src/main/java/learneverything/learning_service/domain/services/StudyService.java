package learneverything.learning_service.domain.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudyService {
    List<Object> study(Integer id,String userId,Integer strategyId);
    Object evaluate(Object result,String userId);
}
