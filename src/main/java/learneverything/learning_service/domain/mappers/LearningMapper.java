package learneverything.learning_service.domain.mappers;

import learneverything.learning_service.database.entities.LearningEntity;
import learneverything.learning_service.domain.dtos.learning.LearningDTO;
import learneverything.learning_service.domain.enums.LearningType;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LearningMapper {
    Map<Class,ILearningMapper> mapperMap = new HashMap<>();

    @Autowired
    public LearningMapper(List<ILearningMapper> learningMappers){
        for (ILearningMapper mapper : learningMappers){
            mapperMap.put(mapper.getClass(),mapper);
        }
    }

    public LearningDTO entityToDto(LearningEntity entity){
        Class<? extends LearningEntity> entityClass = entity.getClass();
        LearningType learningType = LearningType.getLearningTypeByEntityClass(entityClass);
        Class learningMapperClass = learningType.getMapperClass();
        ILearningMapper learningMapper = mapperMap.get(learningMapperClass);

        return learningMapper.entityToDto(entity);
    }

    @SneakyThrows
    public LearningEntity dtoToEntity(LearningDTO dto){
        Class dtoClass = dto.getClass();
        LearningType learningType = LearningType.getLearningTypeByDtoClass(dtoClass);
        Class learningMapperClass = learningType.getMapperClass();
        ILearningMapper learningMapper = mapperMap.get(learningMapperClass);

        return learningMapper.dtoToEntity(dto);
    }
}
