package backend.controller;

import backend.dto.SectionDto;
import backend.model.Section;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectionController {

    ResponseEntity<List<SectionDto>> getAllSections();

    ResponseEntity<SectionDto> getSectionById(Long id);

}
