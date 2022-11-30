package backend.service;

import backend.model.Section;
import backend.model.SectionBlock;

import java.util.List;
import java.util.Set;

public interface SectionService {

	List<Section> getAllSections();

	Section getSectionById(Long id);

}
