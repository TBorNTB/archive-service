package com.sejong.archiveservice.application.internal;


import static com.sejong.archiveservice.application.exception.ExceptionType.EXTERNAL_SERVICE_ERROR;

import com.sejong.archiveservice.application.exception.BaseException;
import com.sejong.archiveservice.client.UserClient;
import com.sejong.archiveservice.client.dto.UserNameInfo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserExternalService {

    private final UserClient userClient;

    @CircuitBreaker(name = "user-circuit-breaker", fallbackMethod = "validateExistenceFallback")
    public void validateExistence(String username, List<String> collaboratorUsernames) {
        ResponseEntity<Boolean> response = userClient.exists(username, collaboratorUsernames);
        if (response.getBody() != Boolean.TRUE) {
            throw new BaseException(EXTERNAL_SERVICE_ERROR);
        }
    }

    private void validateExistenceFallback(String username, List<String> collaboratorUsernames, Throwable t) {
        log.info("fallback method is called. userName: {}, collaboratorUserCount : {}", username, collaboratorUsernames.size());
        if (t instanceof ApiException) {
            throw (ApiException) t;
        }

        throw new BaseException(EXTERNAL_SERVICE_ERROR);
    }

    @CircuitBreaker(name = "user-circuit-breaker", fallbackMethod = "validateExistenceFallback")
    public void validateExistence(String username) {
        ResponseEntity<Boolean> response = userClient.exists(username);
        if (response.getBody() != Boolean.TRUE) {
            throw new BaseException(EXTERNAL_SERVICE_ERROR);
        }
    }

    private void validateExistenceFallback(String username, Throwable t) {
        if (t instanceof ApiException) {
            throw (ApiException) t;
        }

        throw new BaseException(EXTERNAL_SERVICE_ERROR);
    }

    @CircuitBreaker(name = "user-circuit-breaker", fallbackMethod = "getAllUsernamesFallback")
    public Map<String, UserNameInfo> getAllUsernames(List<String> usernames) {
        ResponseEntity<Map<String, UserNameInfo>> response = userClient.getUserNameInfos(usernames);
        if (response.getBody() == null || response.getBody().isEmpty()) {
            throw new BaseException(EXTERNAL_SERVICE_ERROR);
        }
        return response.getBody();
    }

    private Map<String, String>  getAllUsernamesFallback(List<String> usernames, Throwable t) {
        log.info("getAllUsernamesFallback 작동");
        if (t instanceof ApiException) {
            throw (ApiException) t;
        }

        throw new BaseException(EXTERNAL_SERVICE_ERROR);
    }
}
