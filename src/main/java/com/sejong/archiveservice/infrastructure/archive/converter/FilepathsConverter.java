package com.sejong.archiveservice.infrastructure.archive.converter;

import com.sejong.archiveservice.core.common.Filepaths;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FilepathsConverter implements AttributeConverter<Filepaths, String> {


    @Override
    public String convertToDatabaseColumn(Filepaths filepaths) {
        if (filepaths == null) {
            return null;
        }
        return filepaths.toString();
    }

    @Override
    public Filepaths convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Filepaths.of(dbData);
    }
}
