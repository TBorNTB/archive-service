package com.sejong.archiveservice.infrastructure.csknowledge;

import com.sejong.archiveservice.core.csknowledge.CsKnowledge;
import com.sejong.archiveservice.core.csknowledge.CsKnowledgeRepository;
import com.sejong.archiveservice.core.csknowledge.TechCategory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CsKnowledgeRepositoryImpl implements CsKnowledgeRepository {

    private final CsKnowledgeJpaRepository repository;

    @Override
    public List<CsKnowledge> findAllByTechCategory(TechCategory techCategory) {
        return repository
                .findAllByTechCategory(techCategory).stream()
                .map(CsKnowledgeEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<CsKnowledge> findUnsentKnowledge(TechCategory categoryName, String email) {
        Optional<CsKnowledgeEntity> randomUnsent = repository.findRandomUnsent(categoryName.name(), email);
        return randomUnsent.map(CsKnowledgeEntity::toDomain);
    }
}
