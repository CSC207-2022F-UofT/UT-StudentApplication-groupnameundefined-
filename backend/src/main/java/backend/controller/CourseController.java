package backend.controller;

import java.util.List;

import backend.form.CourseForm.*;
import org.springframework.http.ResponseEntity;

import backend.dto.CourseDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/course")
public interface CourseController {

	@PostMapping("/load-courses")
	ResponseEntity<List<CourseDto>> loadCourses(@RequestBody @Valid LoadCoursesForm input);

	@GetMapping("/")
	ResponseEntity<List<CourseDto>> getAllCourses();

	@GetMapping("/{id}")
	ResponseEntity<CourseDto> getCourseById(@PathVariable Long id);

}
