package backend.service;

import java.util.List;

import backend.form.CourseForm.*;

import backend.model.Course;

public interface CourseService {

	/**
	 * @param input An input defined by LoadCoursesForm
	 * @return All courses from the table course after loading
	 * @throws RuntimeException if there was error loading the resources
	 */
	List<Course> loadCourses(LoadCoursesForm input);

	/**
	 * @return All Courses from the courses table
	 */
	List<Course> getAllCourses();

	/**
	 * @param id ID of the course to find
	 * @return The designated course with the given id
	 * @throws backend.exception.exceptions.EntityNotFoundException if the course could not be found
	 */
	Course getCourseById(Long id);

}
