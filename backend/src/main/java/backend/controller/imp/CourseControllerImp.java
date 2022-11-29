package backend.controller.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import backend.controller.CourseController;
import backend.controller.SectionController;
import backend.dto.CourseDto;
import backend.mappers.CourseMapper;
import backend.model.Block;
import backend.model.Course;
import backend.service.CourseService;
import io.micrometer.core.ipc.http.HttpSender.Response;

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
    @GetMapping("/load-courses")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> loadCourses() {
        try {
            courseService.loadCourses();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            List<CourseDto> courseDtos = courseMapper.toDtoList(courses);
            return new ResponseEntity<>(courseDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        try {
            Optional<Course> course = courseService.getCourseById(id);

            if (course.isPresent()) {
                CourseDto courseDto = courseMapper.toDto(course.get());
                return new ResponseEntity<>(courseDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
