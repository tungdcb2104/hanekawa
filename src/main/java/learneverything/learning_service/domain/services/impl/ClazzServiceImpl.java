package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.ChapterEntity;
import learneverything.learning_service.database.entities.ClazzEntity;
import learneverything.learning_service.database.repositories.ChapterRepository;
import learneverything.learning_service.database.repositories.ClazzRepository;
import learneverything.learning_service.domain.dtos.chapter.ChapterDTO;
import learneverything.learning_service.domain.dtos.clazz.ClazzDTO;
import learneverything.learning_service.domain.dtos.clazz.CreateClazzRequestDTO;
import learneverything.learning_service.domain.dtos.clazz.UpdateClazzRequestDTO;
import learneverything.learning_service.domain.mappers.ChapterMapper;
import learneverything.learning_service.domain.mappers.ClazzMapper;
import learneverything.learning_service.domain.services.ClazzService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClazzServiceImpl implements ClazzService {
    private final ClazzRepository clazzRepository;
    private final ClazzMapper clazzMapper;
    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;

    @Override
    public ClazzDTO get(Integer id) {
        ClazzEntity clazz = clazzRepository.findById(id).orElseThrow(()->new BaseException(Error.NOT_FOUND_LESSON,id.toString()));
        ClazzDTO clazzDTO = clazzMapper.toDTO(clazz);

        List<ChapterEntity> chapterEntities = chapterRepository.findActiveChaptersOfClazz(id);
        if (chapterEntities.size() > 0){
            List<ChapterDTO> chapterDTOS = chapterEntities.stream().map(chapterMapper::toDTO).toList();
            clazzDTO.setListChapter(chapterDTOS);
        }

        return clazzDTO;
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
