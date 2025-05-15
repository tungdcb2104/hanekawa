package learneverything.learning_service.domain.services;

import learneverything.learning_service.domain.dtos.chapter.ChapterDTO;
import learneverything.learning_service.domain.dtos.chapter.CreateChapterRequestDTO;
import learneverything.learning_service.domain.dtos.chapter.UpdateChapterRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ChapterService {
    ChapterDTO get(Integer id);
    Object create(CreateChapterRequestDTO createChapterRequest);
    String delete(Integer id);
    Object update(Integer id, UpdateChapterRequestDTO updateChapterRequest);
}
