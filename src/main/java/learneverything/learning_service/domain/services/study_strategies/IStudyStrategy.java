package learneverything.learning_service.domain.services.study_strategies;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudyStrategy {
    List<Object> learn(Integer lessonId,String userId);
}
