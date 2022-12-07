package backend.service;

import java.util.List;

import backend.form.CourseForm.*;

import backend.model.Course;

public interface CourseService {

	void loadCourses(LoadCoursesForm input);

	List<Course> getAllCourses();

	Course getCourseById(Long id);

}
