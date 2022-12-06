package backend.mappers;

import backend.dto.StudentProfileDto;
import backend.model.StudentProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {SocialMediaProfileMapper.class, HabitMapper.class})
public interface StudentProfileMapper {

	StudentProfileDto toDto(StudentProfile studentProfile);

	List<StudentProfileDto> toDtoList(List<StudentProfile> studentProfiles);

}
