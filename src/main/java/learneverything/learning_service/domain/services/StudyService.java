package learneverything.learning_service.domain.services;

import learneverything.learning_service.domain.services.study_strategies.StudyStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudyService {
    List<Object> study(StudyStrategy studyStrategy,Integer id);
}
