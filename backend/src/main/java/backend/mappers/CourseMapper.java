package backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import backend.dto.CourseDto;
import backend.model.Course;

import java.util.List;

@Mapper(uses = {SectionMapper.class, SectionBlockMapper.class})
public interface CourseMapper {

    CourseDto toDto(Course course);

    List<CourseDto> toDtoList(List<Course> courses);

}
