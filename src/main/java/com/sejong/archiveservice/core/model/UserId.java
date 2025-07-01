package com.sejong.archiveservice.core.model;

public class UserId {
    private Long value;
    private UserId(Long value) {
        // validate(userId) // Todo(sigmaith): user-service에 유효한 id인지 검증 필요.
        this.value = value;
    }

    public static UserId of(Long value) {
        return new UserId(value);
    }

    public Long userId() { return value; }
}
