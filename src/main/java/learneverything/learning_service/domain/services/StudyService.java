package learneverything.learning_service.domain.services;

import learneverything.learning_service.domain.dtos.learning_result.LessonResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudyService {
    List<Object> study(Integer id,String userId,Integer strategyId);
    Object evaluate(LessonResultDTO result, String userId);
}
