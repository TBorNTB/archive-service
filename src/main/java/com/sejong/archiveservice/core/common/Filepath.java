package com.sejong.archiveservice.core.common;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

public class Filepath {
    private final String path;

    private Filepath(String path) {
        this.path = path;
    }

    @JsonCreator
    public static Filepath of(String path) {
        return new Filepath(path);
    }

    public String path() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filepath filepath = (Filepath) o;
        return Objects.equals(path, filepath.path);
    }

    @Override
    @JsonValue
    public int hashCode() {
        return Objects.hash(path);
    }
}
