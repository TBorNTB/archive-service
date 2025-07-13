package com.sejong.archiveservice.infrastructure.archive.repository;

import com.sejong.archiveservice.core.common.pagination.CursorPageRequest;
import com.sejong.archiveservice.core.common.pagination.CursorPageResponse;
import com.sejong.archiveservice.core.common.pagination.CustomPageRequest;
import com.sejong.archiveservice.core.common.pagination.OffsetPageResponse;
import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.core.repository.ArchiveRepository;
import com.sejong.archiveservice.infrastructure.archive.entity.ArchiveEntity;
import com.sejong.archiveservice.infrastructure.archive.mapper.ArchiveMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArchiveRepositoryImpl implements ArchiveRepository {
    private final ArchiveJpaRepository archiveJpaRepository;

    @Override
    public Archive save(Archive archive) {
        ArchiveEntity entity = ArchiveMapper.toEntity(archive);
        ArchiveEntity archiveEntity = archiveJpaRepository.save(entity);
        return ArchiveMapper.toDomain(archiveEntity);
    }

    @Override
    public boolean existsArchive(Long archiveId) {
        return archiveJpaRepository.existsById(archiveId);
    }

    @Override
    public Archive findBy(Long archiveId) {
        ArchiveEntity archiveEntity = archiveJpaRepository.findById(archiveId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아카이브 ID"));
        return ArchiveMapper.toDomain(archiveEntity);
    }

    @Override
    public Archive update(Archive archive) {
        ArchiveEntity entity = ArchiveMapper.toEntity(archive);
        return ArchiveMapper.toDomain(archiveJpaRepository.save(entity));
    }


    @Override
    public void delete(Archive archive) {
        archiveJpaRepository.deleteById(archive.getId());
    }

    @Override
    public OffsetPageResponse<List<Archive>> findAllWithOffset(CustomPageRequest customPageRequest) {
        Pageable pageable = PageRequest.of(customPageRequest.getPage(),
                customPageRequest.getSize(),
                Direction.valueOf(customPageRequest.getDirection().name()),
                customPageRequest.getSortBy());

        Page<ArchiveEntity> archiveEntities = archiveJpaRepository.findAll(pageable);

        List<Archive> archives = archiveEntities.stream()
                .map(ArchiveMapper::toDomain)
                .toList();

        return OffsetPageResponse.ok(archiveEntities.getNumber(), archiveEntities.getTotalPages(), archives);
    }

    @Override
    public CursorPageResponse<List<Archive>> findAllWithCursor(CursorPageRequest cursorPageRequest) {
        Pageable pageable = PageRequest.of(0, cursorPageRequest.getSize() + 1); // +1로 다음 페이지 존재 여부 확인

        List<ArchiveEntity> entities = getCursorBasedEntities(cursorPageRequest, pageable);

        // 실제 요청한 크기보다 많이 조회되면 다음 페이지가 존재
        boolean hasNext = entities.size() > cursorPageRequest.getSize();

        // 실제 반환할 데이터는 요청한 크기만큼만
        List<ArchiveEntity> resultEntities = hasNext ?
                entities.subList(0, cursorPageRequest.getSize()) : entities; // Todo: 아예 sql로 limit

        List<Archive> archives = resultEntities.stream()
                .map(ArchiveMapper::toDomain)
                .toList();

        // 다음 커서 계산
        Long nextCursor = hasNext && !archives.isEmpty() ?
                archives.get(archives.size() - 1).getId() : null;

        return CursorPageResponse.ok(nextCursor, hasNext, archives);
    }

    private List<ArchiveEntity> getCursorBasedEntities(CursorPageRequest request, Pageable pageable) {
        boolean isDesc = request.getDirection() == CursorPageRequest.SortDirection.DESC;

        if (request.getCursor() == null) {
            // 첫 페이지
            return isDesc ?
                    archiveJpaRepository.findFirstPageDesc(pageable) :
                    archiveJpaRepository.findFirstPageAsc(pageable);
        } else {
            // 커서 기반 페이지
            return isDesc ?
                    archiveJpaRepository.findByCursorDesc(request.getCursor(), pageable) :
                    archiveJpaRepository.findByCursorAsc(request.getCursor(), pageable);
        }
    }
}
