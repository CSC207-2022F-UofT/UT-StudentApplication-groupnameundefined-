package backend.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import backend.dto.TimetableDto;
import backend.model.Timetable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import backend.form.TimetableForm.*;
import backend.model.Block;

public interface TimetableController {

    ResponseEntity<TimetableDto> createTimetable(Long studentProfileId, MultipartFile file);

    ResponseEntity<List<Timetable>> getAllTimetables();

    ResponseEntity<TimetableDto> getTimetableById(Long id);

}
