package backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import antlr.collections.List;
import backend.dto.CourseDto;
import backend.model.Course;

@Mapper(uses = { SectionMapper.class, SectionBlockMapper.class })
public interface CourseMapper {

    CourseDto courseToDto(Course course);

}
