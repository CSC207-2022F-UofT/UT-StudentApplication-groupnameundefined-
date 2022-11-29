package backend.controller;

import backend.model.Section;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectionController {

    ResponseEntity<List<Section>> getAllSections();

    ResponseEntity<Section> getSectionById(Long id);
}
