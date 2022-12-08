package backend.controller.imp;

import backend.dto.SectionBlockDto;
import backend.mappers.SectionBlockMapper;
import backend.service.SectionBlockService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.controller.SectionBlockController;
import backend.model.SectionBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class SectionBlockControllerImp implements SectionBlockController {

    private final Logger logger;

    private final SectionBlockService sectionBlockService;
    private final SectionBlockMapper sectionBlockMapper;

    @Autowired
    public SectionBlockControllerImp(
            Logger logger, SectionBlockService sectionBlockService, SectionBlockMapper sectionBlockMapper
    ) {
        this.logger = logger;
        this.sectionBlockService = sectionBlockService;
        this.sectionBlockMapper = sectionBlockMapper;
    }

    @Override
    public ResponseEntity<List<SectionBlockDto>> getAllSectionBlocks() {
        List<SectionBlock> sectionBlocks = sectionBlockService.getAllSectionBlocks();
        List<SectionBlockDto> sectionBlockDtos = sectionBlockMapper.toDtoList(sectionBlocks);

        return new ResponseEntity<>(sectionBlockDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SectionBlockDto> getSectionBlockById(Long id) {
        SectionBlock sectionBlock = sectionBlockService.getSectionBlockById(id);
        SectionBlockDto sectionBlockDto = sectionBlockMapper.toDto(sectionBlock);

        return new ResponseEntity<>(sectionBlockDto, HttpStatus.OK);
    }


}
