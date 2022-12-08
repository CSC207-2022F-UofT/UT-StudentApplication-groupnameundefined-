package backend.service;

import backend.model.SectionBlock;

import java.util.List;
import java.util.Set;

public interface SectionBlockService {

	/**
	 * @return All SectionBlocks from the section_blocks table
	 */
	List<SectionBlock> getAllSectionBlocks();

	/**
	 * @param course  Course code of the SectionBlock
	 * @param section Section code for the SectionBlock
	 * @return List of sections that matches the given codes
	 */
	List<SectionBlock> getSectionBlocksByCode(String course, String section);

	/**
	 * @param id ID of the SectionBlock to find
	 * @return The designated SectionBlock with the given id
	 * @throws backend.exception.exceptions.EntityNotFoundException if the SectionBlock could not be found
	 */
	SectionBlock getSectionBlockById(Long id);
}
