package backend.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import backend.model.Block;
import backend.model.SectionBlock;
import backend.model.StudentProfile;
import org.springframework.web.multipart.MultipartFile;

import backend.model.Timetable;

public interface TimetableService {

	List<Timetable> getAllTimetables();

	Timetable getTimetableById(Long id);

	List<Map<String, String>> parseIcs(MultipartFile file);

}
