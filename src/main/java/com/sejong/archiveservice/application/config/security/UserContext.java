package com.sejong.archiveservice.application.config.security;

import lombok.Getter;

@Getter
public class UserContext {
    private final String username;
    private final String userRole;

    private UserContext(String username, String userRole) {
        this.username = username;
        this.userRole = userRole;
    }

    public static UserContext of(String username, String userRole) {
        return new UserContext(username, userRole);
    }
}
