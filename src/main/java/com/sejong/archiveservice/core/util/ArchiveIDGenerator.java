package com.sejong.archiveservice.core.util;

import com.sejong.archiveservice.core.model.ArchiveID;
import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArchiveIDGenerator {
    public static ArchiveID generate() {
        return ArchiveID.of(UUID.randomUUID().toString());
    }

}
