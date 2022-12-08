package backend.controller.imp;

import backend.dto.SectionDto;
import backend.mappers.SectionMapper;
import backend.service.SectionService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.controller.SectionController;
import backend.model.Section;

import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<List<SectionDto>> getAllSections() {
        List<Section> sections = sectionService.getAllSections();
        List<SectionDto> sectionDtos = sectionMapper.toDtoList(sections);

        return new ResponseEntity<>(sectionDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SectionDto> getSectionById(Long id) {
        Section section = sectionService.getSectionById(id);
        SectionDto sectionDto = sectionMapper.toDto(section);

        return new ResponseEntity<>(sectionDto, HttpStatus.OK);
    }
}
