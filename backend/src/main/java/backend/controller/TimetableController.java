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

    ResponseEntity<List<Map<String, String>>> parseIcs(MultipartFile file);

    ResponseEntity<TimetableDto> uploadTimetable(Long studentProfileId, MultipartFile file);

}
