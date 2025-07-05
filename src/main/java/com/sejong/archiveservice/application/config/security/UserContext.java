package com.sejong.archiveservice.application.config.security;

import lombok.Getter;

@Getter
public class UserContext {
    private final String userId;
    private final String userRole;

    private UserContext(String userId, String userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }

    public static UserContext of(String userId, String userRole) {
        return new UserContext(userId, userRole);
    }
}
