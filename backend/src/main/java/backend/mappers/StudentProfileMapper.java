package backend.mappers;

import backend.dto.StudentProfileDto;
import backend.model.StudentProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(uses = {SocialMediaProfileMapper.class, HabitMapper.class})
public interface StudentProfileMapper {

	@Mapping(target = "userId", source = "user.id")
	@Mapping(target = "userName", source = "user.name")
	StudentProfileDto toDto(StudentProfile studentProfile);

	List<StudentProfileDto> toDtoList(List<StudentProfile> studentProfiles);

}
