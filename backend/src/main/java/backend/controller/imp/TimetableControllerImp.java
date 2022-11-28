package backend.controller.imp;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import backend.dto.TimetableDto;
import backend.mappers.TimetableMapper;
import backend.model.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.controller.TimetableController;
import backend.form.TimetableForm.*;
import backend.model.Block;
import backend.service.TimetableService;

@CrossOrigin
@RestController
@RequestMapping("/api/timetable")
public class TimetableControllerImp implements TimetableController {

    @Autowired
    TimetableService timetableService;

    @Autowired
    TimetableMapper timetableMapper;

    @Override
    @PostMapping("/")
    public ResponseEntity<TimetableDto> uploadTimetable(@RequestParam("studentProfileId") Long studentProfileId, @RequestParam("file") MultipartFile file) {
        try {
            Timetable timetable = timetableService.createTimetable(studentProfileId, file);
            TimetableDto timetableDto = timetableMapper.timetableToDto(timetable);

            return new ResponseEntity<>(timetableDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/parse-ics")
    public ResponseEntity<List<Map<String, String>>> parseIcs(@RequestParam("file") MultipartFile file) {
        try {
            List<Map<String, String>> sectionData = timetableService.parseIcs(file);

            return new ResponseEntity<>(sectionData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
