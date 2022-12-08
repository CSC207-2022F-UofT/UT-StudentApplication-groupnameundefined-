package backend.controller;

import java.util.List;

import backend.form.CourseForm.*;
import org.springframework.http.ResponseEntity;

import backend.dto.CourseDto;

public interface CourseController {

	ResponseEntity<List<CourseDto>> loadCourses(LoadCoursesForm input);

	ResponseEntity<List<CourseDto>> getAllCourses();

	ResponseEntity<CourseDto> getCourseById(Long id);

}
