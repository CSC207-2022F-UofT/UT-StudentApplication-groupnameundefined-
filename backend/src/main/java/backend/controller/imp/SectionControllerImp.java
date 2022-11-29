package backend.controller.imp;

import backend.dto.SectionDto;
import backend.mappers.SectionMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.controller.SectionController;
import backend.model.Section;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/section")
public class SectionControllerImp implements SectionController {

    private final Logger logger;

    private final SectionService sectionService;
    private final SectionMapper sectionMapper;

    @Autowired
    public SectionControllerImp(Logger logger, SectionService sectionService, SectionMapper sectionMapper) {
        this.logger = logger;
        this.sectionService = sectionService;
        this.sectionMapper = sectionMapper;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<SectionDto>> getAllSections() {
        try {
            List<Section> sections = sectionService.getAllSections();
            List<SectionDto> sectionDtos = sectionMapper.toDtoList(sections);

            return new ResponseEntity<>(sectionDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable Long id) {
        try {
            Optional<Section> section = sectionService.getSectionById(id);

            if (section.isPresent()) {
                SectionDto sectionDto = sectionMapper.toDto(section.get());
                return new ResponseEntity<>(sectionDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
