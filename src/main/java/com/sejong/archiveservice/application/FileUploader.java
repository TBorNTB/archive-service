package com.sejong.archiveservice.application;

import com.sejong.archiveservice.core.common.Filepath;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
    Filepath upload(MultipartFile file, String dirName);
    void delete(Filepath filePath);
}
