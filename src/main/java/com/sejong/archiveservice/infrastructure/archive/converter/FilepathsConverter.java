package com.sejong.archiveservice.infrastructure.archive.converter;

import com.sejong.archiveservice.core.common.Filepaths;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FilepathsConverter implements AttributeConverter<Filepaths, String> {


    @Override
    public String convertToDatabaseColumn(Filepaths filepaths) {
        return filepaths.toString();
    }

    @Override
    public Filepaths convertToEntityAttribute(String dbData) {
        return Filepaths.of(dbData);
    }
}
