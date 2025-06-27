package com.sejong.archiveservice.core.common;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
    String upload(MultipartFile file, String dirName);
    void delete(String filePath);
}
