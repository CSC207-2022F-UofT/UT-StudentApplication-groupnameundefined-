package backend.service.imp;

import backend.model.SectionBlock;
import backend.repository.SectionBlockRepository;
import backend.service.SectionBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionBlockServiceImp implements SectionBlockService {

    @Autowired
    SectionBlockRepository sectionBlockRepository;

    @Override
    public List<SectionBlock> getAllSectionBlocks() {
        return new ArrayList<SectionBlock>(sectionBlockRepository.findAll());
    }

    @Override
    public List<SectionBlock> getSectionBlocksByCode(String course, String section) {
        return new ArrayList<SectionBlock>(sectionBlockRepository.findByCode(course, section));
    }
}
