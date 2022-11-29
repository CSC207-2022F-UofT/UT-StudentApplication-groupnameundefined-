package backend.controller.imp;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import backend.dto.TimetableDto;
import backend.mappers.TimetableMapper;
import backend.model.Timetable;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import backend.controller.TimetableController;
import backend.form.TimetableForm.*;
import backend.model.Block;
import backend.service.TimetableService;

@CrossOrigin
@RestController
@RequestMapping("/api/timetable")
public class TimetableControllerImp implements TimetableController {

    private Logger logger;

    private TimetableService timetableService;
    private TimetableMapper timetableMapper;

    public TimetableControllerImp(Logger logger, TimetableService timetableService, TimetableMapper timetableMapper) {
        this.logger = logger;
        this.timetableService = timetableService;
        this.timetableMapper = timetableMapper;
    }

    @Override
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TimetableDto> createTimetable(@RequestPart Long studentProfileId,
                                                        @RequestPart MultipartFile file) {
        try {
            Timetable timetable = timetableService.createTimetable(studentProfileId, file);
            TimetableDto timetableDto = timetableMapper.toDto(timetable);

            return new ResponseEntity<>(timetableDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<TimetableDto>> getAllTimetables() {
        try {
            List<Timetable> timetables = timetableService.getAllTimetables();
            List<TimetableDto> timetableDtos = timetableMapper.toDtoList(timetables);

            return new ResponseEntity<>(timetableDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TimetableDto> getTimetableById(@PathVariable Long id) {
        try {
            Optional<Timetable> timetable = timetableService.getTimetableByid(id);

            if (timetable.isPresent()) {
                TimetableDto timetableDto = timetableMapper.toDto(timetable.get());
                return new ResponseEntity<>(timetableDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
