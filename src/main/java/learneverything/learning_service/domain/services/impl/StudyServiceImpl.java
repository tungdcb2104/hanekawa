package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.repositories.LessonRepository;
import learneverything.learning_service.domain.dtos.learning_result.LessonResultDTO;
import learneverything.learning_service.domain.services.StudyService;
import learneverything.learning_service.domain.services.study_strategies.IStudyStrategy;
import learneverything.learning_service.domain.services.study_strategies.StrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Object> study(Integer id,String userId, Integer strategyId) {
        IStudyStrategy strategy = getStudyStrategyById(strategyId);
        return strategy.learn(id,userId);
    }

    @Override
    public Object evaluate(LessonResultDTO result, String userId) {
        IStudyStrategy strategy = getStudyStrategyById(result.getStrategyId());
        return strategy.evaluate(result,userId);
    }

    private IStudyStrategy getStudyStrategyById(Integer strategyId){
        StrategyType strategyType = StrategyType.findById(strategyId);

        if (studyStrategyMap.containsKey(strategyType.getStrategy())){
            return studyStrategyMap.get(strategyType.getStrategy());
        }else {
            throw new BaseException(Error.UNAVAILABLE_STRATEGY);
        }
    }
}
