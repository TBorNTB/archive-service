package com.sejong.archiveservice.core.csknowledge;

import java.util.List;
import java.util.Optional;

public interface CsKnowledgeRepository {
    List<CsKnowledge> findAllByTechCategory(TechCategory techCategory);

    Optional<CsKnowledge> findUnsentKnowledge(TechCategory categoryName, String email);
}
