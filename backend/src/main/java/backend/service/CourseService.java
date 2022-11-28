package backend.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.ParseException;

import backend.model.Course;

public interface CourseService {

    void loadCourses() throws FileNotFoundException, IOException, ParseException;

    List<Course> getAllCourses();

}
