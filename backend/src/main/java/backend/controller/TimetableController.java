package backend.controller;

import java.util.List;

import backend.dto.TimetableDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/timetable")
public interface TimetableController {

    @GetMapping("/")
    ResponseEntity<List<TimetableDto>> getAllTimetables();

    @GetMapping("/{id}")
    ResponseEntity<TimetableDto> getTimetableById(@PathVariable Long id);

}
