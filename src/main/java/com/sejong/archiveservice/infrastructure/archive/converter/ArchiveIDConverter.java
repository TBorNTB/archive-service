package com.sejong.archiveservice.infrastructure.archive.converter;

import com.sejong.archiveservice.core.model.ArchiveID;
import jakarta.persistence.AttributeConverter;
import java.nio.ByteBuffer;
import java.util.UUID;

public class ArchiveIDConverter implements AttributeConverter<ArchiveID, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(ArchiveID attribute) {
        if (attribute == null) {
            return null;
        }
        UUID uuid = UUID.fromString(attribute.getValue());
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public ArchiveID convertToEntityAttribute(byte[] dbData) {
        if (dbData == null || dbData.length != 16) {
            return null;
        }
        ByteBuffer bb = ByteBuffer.wrap(dbData);
        long high = bb.getLong();
        long low = bb.getLong();
        UUID uuid = new UUID(high, low);
        return ArchiveID.of(uuid.toString());
    }
}
