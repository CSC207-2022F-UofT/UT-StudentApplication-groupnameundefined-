package backend.service.imp;

import backend.exception.exceptions.EntityNotFoundException;
import backend.model.SectionBlock;
import backend.repository.SectionBlockRepository;
import backend.service.SectionBlockService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionBlockServiceImp implements SectionBlockService {

	private final Logger logger;

	private final SectionBlockRepository sectionBlockRepository;

	@Autowired
	public SectionBlockServiceImp(Logger logger, SectionBlockRepository sectionBlockRepository) {
		this.logger = logger;
		this.sectionBlockRepository = sectionBlockRepository;
	}

	@Override
	public List<SectionBlock> getAllSectionBlocks() {
		return sectionBlockRepository.findAll();
	}

	@Override
	public List<SectionBlock> getSectionBlocksByCode(String course, String section) {
		return sectionBlockRepository.findByCode(course, section);
	}

	@Override
	public SectionBlock getSectionBlockById(Long id) {
		Optional<SectionBlock> sectionBlock = sectionBlockRepository.findById(id);
		if (sectionBlock.isPresent()) {
			return sectionBlock.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find section block with id '%d'.", id), SectionBlock.class);
	}
}
