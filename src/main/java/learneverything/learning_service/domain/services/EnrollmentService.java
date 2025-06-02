package learneverything.learning_service.domain.services;

import org.springframework.stereotype.Service;

@Service
public interface EnrollmentService {
    String register(Integer clazzId);
    String unregister(Integer clazzId);
}
