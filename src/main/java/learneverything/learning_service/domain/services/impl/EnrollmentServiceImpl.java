package learneverything.learning_service.domain.services.impl;

import learneverything.learning_service.application.exceptions.BaseException;
import learneverything.learning_service.application.exceptions.Error;
import learneverything.learning_service.database.entities.EnrollmentEntity;
import learneverything.learning_service.database.repositories.EnrollmentRepository;
import learneverything.learning_service.domain.dtos.ResponseConstant;
import learneverything.learning_service.domain.services.EnrollmentService;
import learneverything.learning_service.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;


    @Override
    public String register(Integer clazzId) {
        String userId = CommonUtils.getUserId();
        List<EnrollmentEntity> enrollmentEntities = enrollmentRepository.findByUserIdAndClazzId(userId,clazzId);
        if (CommonUtils.isNullOrEmpty(enrollmentEntities)){
            EnrollmentEntity entity = new EnrollmentEntity();
            entity.setClazzId(clazzId);
            entity.setUserId(userId);
            entity.setStatus(1);
            enrollmentRepository.save(entity);
        }else {
            if (enrollmentEntities.get(0).getStatus() == 1){
                throw new BaseException(Error.BAD_REQUEST);
            }
            enrollmentEntities.get(0).setStatus(1);
            enrollmentRepository.saveAll(enrollmentEntities);
        }


        return ResponseConstant.OK;
    }

    @Override
    public String unregister(Integer clazzId) {
        String userId = CommonUtils.getUserId();
        List<EnrollmentEntity> enrollmentEntities = enrollmentRepository.findByUserIdAndClazzId(userId,clazzId);
        if (CommonUtils.isNullOrEmpty(enrollmentEntities)){
            throw new BaseException(Error.BAD_REQUEST);
        }

        if (enrollmentEntities.get(0).getStatus() == 0){
            throw new BaseException(Error.BAD_REQUEST);
        }else {
            enrollmentEntities.get(0).setStatus(0);
        }
        enrollmentRepository.saveAll(enrollmentEntities);

        return ResponseConstant.OK;
    }
}
