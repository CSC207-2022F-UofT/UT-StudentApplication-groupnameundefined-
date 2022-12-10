package backend.controller;

import backend.dto.SectionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/section")
public interface SectionController {

    @GetMapping("/")
    ResponseEntity<List<SectionDto>> getAllSections();

    @GetMapping("/{id}")
    ResponseEntity<SectionDto> getSectionById(@PathVariable Long id);

}
