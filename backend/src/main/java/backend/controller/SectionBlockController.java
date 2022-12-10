package backend.controller;

import backend.dto.SectionBlockDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/section-block")
public interface SectionBlockController {

    @GetMapping("/")
    ResponseEntity<List<SectionBlockDto>> getAllSectionBlocks();

    @GetMapping("/{id}")
    ResponseEntity<SectionBlockDto> getSectionBlockById(@PathVariable Long id);

}
