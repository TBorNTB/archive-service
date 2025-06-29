package com.sejong.archiveservice.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ArchiveID {
    private final String value;

    private ArchiveID(String value) {
        validate(value);
        this.value = value;
    }

    @JsonCreator
    public static ArchiveID of(String value) {
        return new ArchiveID(value);
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Invalid archive ID");
        }
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
