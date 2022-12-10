package backend.service;

import backend.model.Section;
import backend.model.SectionBlock;

import java.util.List;
import java.util.Set;

public interface SectionService {

	/**
	 * @return All Sections from the section table
	 */
	List<Section> getAllSections();

	/**
	 * @param id ID of the Section to find
	 * @return The designated section with the given id.
	 * @throws backend.exception.exceptions.EntityNotFoundException If the section could not be found.
	 */
	Section getSectionById(Long id);

}
