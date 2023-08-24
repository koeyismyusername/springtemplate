package com.github.springtemplate.util;

import com.github.springtemplate.exception.errorcode.ApiErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
public class JpaUtils {
    public static <T, ID> T managedSave(JpaRepository<T, ID> repository, T entity) {
        for (int i = 1; i <= 10; i++) {
            try {
                return repository.save(entity);
            } catch (OptimisticLockingFailureException e) {
                log.warn("{} 저장 중에 충돌이 발생했습니다. {}회 시도", entity.getClass().getSimpleName(), i);
            }
        }

        throw ApiErrorCode.FAIL_TO_SAVE.exception();
    }
}
