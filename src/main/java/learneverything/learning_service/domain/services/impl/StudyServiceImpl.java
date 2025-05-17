package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.LessonEntity;
import learneverything.learning_service.database.repositories.LessonRepository;
import learneverything.learning_service.domain.enums.LearningType;
import learneverything.learning_service.domain.services.StudyService;
import learneverything.learning_service.domain.services.study_strategies.IStudyStrategy;
import learneverything.learning_service.domain.services.study_strategies.StrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static learneverything.learning_service.utils.CommonUtils.*;

@Service
public class StudyServiceImpl implements StudyService {
    private final Map<Class, IStudyStrategy> studyStrategyMap = new HashMap<>();
    private final LessonRepository lessonRepository;

    @Autowired
    public StudyServiceImpl(List<IStudyStrategy> studyStrategies,LessonRepository lessonRepository){
        for (IStudyStrategy strategy : studyStrategies){
            studyStrategyMap.put(strategy.getClass(),strategy);
        }
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Object> study(Integer id, Class strategyClass) {
        LessonEntity lessonEntity = lessonRepository.findById(id)
                .orElseThrow(()->new BaseException(Error.NOT_FOUND_LESSON,String.valueOf(id)));

        LearningType learningType = LearningType.getLearningTypeByName(lessonEntity.getLearningType());
        StrategyType strategyType = StrategyType.findByStrategy(strategyClass);
        if (strategyType == null){
            throw new BaseException(Error.NOT_FOUND_STRATEGY);
        }

        if (!strategyType.getLearningType().equals(learningType)){
            throw new BaseException(Error.INVALID_STRATEGY,strategyClass.getName(),String.valueOf(id));
        }

        IStudyStrategy strategy = studyStrategyMap.get(strategyClass);

        if (Objects.isNull(strategy)){
            throw new BaseException(Error.UNAVAILABLE_STRATEGY);
        }

        return strategy.learn(id);
    }
}
