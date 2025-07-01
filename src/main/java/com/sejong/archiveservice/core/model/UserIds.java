package com.sejong.archiveservice.core.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserIds {
    private List<UserId> userIds;

    private UserIds(List<UserId> userIds) {
        this.userIds = userIds;
    }

    public static UserIds of(String ids) {
        String[] splits = ids.split(",");
        List<Long> convertedIds = Arrays.stream(splits).map(Long::valueOf).toList();
        return UserIds.of(convertedIds);
    }

    public static UserIds of(List<Long> ids) {
        return new UserIds(ids.stream().map(UserId::of).toList());
    }

    @Override
    public String toString() {
        return userIds.stream()
                .map(u -> String.valueOf(u.userId()))
                .collect(Collectors.joining(","));
    }
}
