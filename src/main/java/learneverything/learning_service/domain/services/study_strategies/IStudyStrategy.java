package learneverything.learning_service.domain.services.study_strategies;

import learneverything.learning_service.domain.dtos.learning_result.LessonResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudyStrategy {
    List<Object> learn(Integer lessonId,String userId);
    Object evaluate(LessonResultDTO result, String userId);
}
