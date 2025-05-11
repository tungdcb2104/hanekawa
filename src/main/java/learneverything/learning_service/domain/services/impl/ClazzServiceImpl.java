package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.ClazzEntity;
import learneverything.learning_service.database.repositories.ClazzRepository;
import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.CreateClazzRequestDTO;
import learneverything.learning_service.domain.dtos.clazz.UpdateClazzRequestDTO;
import learneverything.learning_service.domain.mappers.ClazzMapper;
import learneverything.learning_service.domain.services.ClazzService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClazzServiceImpl implements ClazzService {
    private final ClazzRepository clazzRepository;
    private final ClazzMapper clazzMapper;

    @Override
    public ClazzDTO get(Integer id) {
        ClazzEntity clazz = clazzRepository.findById(id).orElseThrow(()->new BaseException(Error.NOT_FOUND_LESSON,id.toString()));
        return clazzMapper.toDTO(clazz);
    }

    @Override
    public Object create(CreateClazzRequestDTO createClazzRequest) {
        ClazzEntity clazz = clazzMapper.createDTOToEntity(createClazzRequest);
        clazz.setAuthorId(0);
        return clazzRepository.save(clazz);
    }

    @Override
    public String delete(Integer id) {
        clazzRepository.deleteById(id);
        return "Successful !";
    }

    @Override
    public Object update(Integer id,UpdateClazzRequestDTO updateClazzRequest) {
        ClazzEntity clazz = clazzMapper.updateDTOToEntity(updateClazzRequest);
        return null;
    }
}
