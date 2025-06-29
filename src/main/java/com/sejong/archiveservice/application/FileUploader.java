package com.sejong.archiveservice.application;

import com.sejong.archiveservice.application.file.PreSignedUrl;
import com.sejong.archiveservice.core.common.Filepath;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
    PreSignedUrl generatePreSignedUrl(String fileName, String contentType, String dirName);
    void delete(Filepath filepath);
    Filepath getFileUrl(String key);
}
