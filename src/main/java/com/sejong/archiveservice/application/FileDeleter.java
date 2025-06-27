package com.sejong.archiveservice.application;

import com.sejong.archiveservice.core.common.Filepath;
import java.util.List;

public interface FileDeleter {
    public void delete(List<Filepath> filepaths);
    public void delete(Filepath filepath);
}
