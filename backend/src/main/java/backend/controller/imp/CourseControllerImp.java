package backend.controller.imp;

import java.util.List;

import backend.form.CourseForm.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.controller.CourseController;
import backend.dto.CourseDto;
import backend.mappers.CourseMapper;
import backend.model.Course;
import backend.service.CourseService;

@CrossOrigin
@RestController
@RequestMapping("/api/course")
public class CourseControllerImp implements CourseController {

	private final Logger logger;

	private final CourseService courseService;
	private final CourseMapper courseMapper;

	@Autowired
	public CourseControllerImp(Logger logger, CourseService courseService, CourseMapper courseMapper) {
		this.logger = logger;
		this.courseService = courseService;
		this.courseMapper = courseMapper;
	}

	@Override
	@PostMapping("/load-courses")
	public ResponseEntity<List<CourseDto>> loadCourses(@RequestBody LoadCoursesForm input) {
		List<Course> courses = courseService.loadCourses(input);
		List<CourseDto> courseDtos = courseMapper.toDtoList(courses);
		
		return new ResponseEntity<>(courseDtos, HttpStatus.OK);
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<List<CourseDto>> getAllCourses() {
		List<Course> courses = courseService.getAllCourses();
		List<CourseDto> courseDtos = courseMapper.toDtoList(courses);

		return new ResponseEntity<>(courseDtos, HttpStatus.OK);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
		Course course = courseService.getCourseById(id);
		CourseDto courseDto = courseMapper.toDto(course);

		return new ResponseEntity<>(courseDto, HttpStatus.OK);
	}
}
