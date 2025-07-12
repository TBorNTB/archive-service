package com.sejong.archiveservice.infrastructure.user;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/users/{userId}/exists")
    boolean exists(@PathVariable("userId") String userId);

    @PostMapping("/users/exists")
    boolean existsUsers(@RequestBody List<String> userIds);
}
