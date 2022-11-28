package backend.controller.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @Autowired
    Logger logger;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseMapper courseMapper;

    @Override
    @GetMapping("/load-courses")
    public ResponseEntity<String> loadCourses() {
        try {
            courseService.loadCourses();

            return new ResponseEntity<String>("Success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            List<CourseDto> courseDtos = new ArrayList<CourseDto>();
            courses.forEach(course -> courseDtos.add(courseMapper.courseToDto(course)));

            return new ResponseEntity<List<CourseDto>>(courseDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
