package backend.service.imp;

import backend.exception.exceptions.EntityNotFoundException;
import backend.model.Section;
import backend.repository.SectionRepository;
import backend.service.SectionService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImp implements SectionService {

	private final Logger logger;

	private final SectionRepository sectionRepository;

	@Autowired
	public SectionServiceImp(Logger logger, SectionRepository sectionRepository) {
		this.logger = logger;
		this.sectionRepository = sectionRepository;
	}

	@Override
	public List<Section> getAllSections() {
		return sectionRepository.findAll();
	}

	@Override
	public Section getSectionById(Long id) {
		Optional<Section> section = sectionRepository.findById(id);
		if (section.isPresent()) {
			return section.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find section with id %d", id), Section.class);
	}
}
