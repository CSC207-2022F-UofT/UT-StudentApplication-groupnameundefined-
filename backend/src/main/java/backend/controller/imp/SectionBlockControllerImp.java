package backend.controller.imp;

import backend.dto.SectionBlockDto;
import backend.mappers.SectionBlockMapper;
import backend.service.SectionBlockService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.controller.SectionBlockController;
import backend.model.SectionBlock;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/section-block")
public class SectionBlockControllerImp implements SectionBlockController {

    @Autowired
    Logger logger;

    @Autowired
    SectionBlockService sectionBlockService;

    @Autowired
    SectionBlockMapper sectionBlockMapper;

    @Override
    @GetMapping("get-all-section-blocks")
    public ResponseEntity<List<SectionBlockDto>> getAllSectionBlocks() {
        try {
            List<SectionBlock> sectionBlocks = sectionBlockService.getAllSectionBlocks();
            List<SectionBlockDto> sectionBlockDtos = new ArrayList<SectionBlockDto>();

            sectionBlocks.forEach(sectionBlock -> sectionBlockDtos.add(sectionBlockMapper.sectionBlockToDto(sectionBlock)));

            return new ResponseEntity<List<SectionBlockDto>>(sectionBlockDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
