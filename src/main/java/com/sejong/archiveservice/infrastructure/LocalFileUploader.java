package com.sejong.archiveservice.infrastructure;

import com.sejong.archiveservice.application.FileUploader;
import com.sejong.archiveservice.core.common.Filepath;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class LocalFileUploader implements FileUploader {

    @Value("${local.file.storage.path}")
    private String storagePath;

    @Override
    @Transactional
    public Filepath upload(MultipartFile file, String dirName) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String fileName = generateFileName(dirName, file.getOriginalFilename());
        String savedFilePath = saveDirectily(file, fileName);

        return Filepath.of(savedFilePath);
    }

    @Override
    @Transactional
    public void delete(Filepath filePath) {

    }

    private String saveDirectily(MultipartFile file, String fileName) {
        try {
            Path dirPath = Paths.get(storagePath, fileName).getParent();
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            Path targetPath = Paths.get(storagePath, fileName);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetPath);
            }

            log.info("파일 저장 성공: {}", targetPath);
            return "/uploads/" + fileName;

        } catch (IOException e) {
            log.error("파일 저장 실패: {}", fileName, e);
            throw new RuntimeException("파일 저장 실패",e);
        }
    }

    private String generateFileName(String dirName, String originalFileName) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        return String.format("%s/%s_%s%s", dirName,
                originalFileName.substring(0, originalFileName.lastIndexOf(".")),
                uuid, fileExtension);
    }
}
