package com.sejong.archiveservice.infrastructure.repository;

import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.core.repository.ArchiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArchiveRepositoryImpl implements ArchiveRepository {
    private final ArchiveJpaRepository archiveJpaRepository;

    @Override
    public Archive save(Archive archive) {
        return null;
    }

//    @Override
//    public Archive save(Archive archive) {
////        archiveMapper.toEntity(); // TODO(sigmaith): model -> entity 매퍼 두기
//        archiveJpaRepository.save(archive); //
//    }
}
