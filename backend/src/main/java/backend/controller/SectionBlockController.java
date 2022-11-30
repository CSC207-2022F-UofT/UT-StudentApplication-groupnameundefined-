package backend.controller;

import backend.dto.SectionBlockDto;
import backend.model.SectionBlock;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectionBlockController {

    ResponseEntity<List<SectionBlockDto>> getAllSectionBlocks();

    ResponseEntity<SectionBlockDto> getSectionBlockById(Long id);
    
}
