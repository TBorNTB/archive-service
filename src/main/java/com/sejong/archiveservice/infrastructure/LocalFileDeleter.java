package com.sejong.archiveservice.infrastructure;

import com.sejong.archiveservice.application.FileDeleter;
import com.sejong.archiveservice.core.common.Filepath;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LocalFileDeleter implements FileDeleter {
    @Value("${local.file.storage.path}")
    private String storagePath;

    public void delete(List<Filepath> filePaths) {
        if (filePaths == null || filePaths.isEmpty()) {
            log.warn("삭제할 파일 경로가 없습니다. (null 또는 비어 있음)");
            return;
        }
        for (Filepath filePath : filePaths) {
            delete(filePath);
        }
    }

    public void delete(Filepath filePath) {
        if (filePath == null || filePath.path().isEmpty()) {
            log.warn("삭제할 파일 경로가 없습니다. (null 또는 비어 있음)");
            return;
        }
        String imageUrl = filePath.path().substring(filePath.path().lastIndexOf("/uploads/") + 9);
        log.info("storagePath: {} imageUrl: {}", storagePath, imageUrl);
        Path targetPath = Paths.get(storagePath, imageUrl); // /var/uploads, profile/loadmap.png_asdfsda.png
        try {
            java.nio.file.Files.deleteIfExists(targetPath);
            log.info("파일 삭제 성공: {}", targetPath);
        } catch (Exception e) {
            log.error("파일 삭제 실패: {}", e.getMessage());
        }
    }
}

