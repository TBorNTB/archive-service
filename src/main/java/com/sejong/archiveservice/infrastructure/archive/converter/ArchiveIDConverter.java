package com.sejong.archiveservice.infrastructure.archive.converter;

import com.sejong.archiveservice.core.model.ArchiveID;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ArchiveIDConverter implements AttributeConverter<ArchiveID, String> {
    @Override
    public String convertToDatabaseColumn(ArchiveID attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public ArchiveID convertToEntityAttribute(String dbData) {
        return dbData != null ? ArchiveID.of(dbData) : null;
    }
}
