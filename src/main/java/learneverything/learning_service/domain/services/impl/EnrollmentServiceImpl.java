package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.database.repositories.EnrollmentRepository;
import learneverything.learning_service.domain.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;


    @Override
    public String register(Integer clazzId) {
        return "";
    }
}
