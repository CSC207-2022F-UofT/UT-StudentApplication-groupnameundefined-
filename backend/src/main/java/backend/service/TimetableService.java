package backend.service;

import java.io.File;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import backend.model.Block;
import backend.model.Timetable;

public interface TimetableService {

    Set<Block> parseIcs(MultipartFile file);

    Timetable createTimetable(Timetable timetable);

}
