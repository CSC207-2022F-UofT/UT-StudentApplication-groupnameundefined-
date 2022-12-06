package backend.service;

import backend.model.SectionBlock;

import java.util.List;
import java.util.Set;

public interface SectionBlockService {
    List<SectionBlock> getAllSectionBlocks();

    List<SectionBlock> getSectionBlocksByCode(String course, String section);

    SectionBlock getSectionBlockById(Long id);
}
