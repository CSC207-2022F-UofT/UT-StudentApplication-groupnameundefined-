package backend.mappers;

import backend.dto.StudentProfileDto;
import backend.model.StudentProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(uses = {SocialMediaProfileMapper.class, HabitMapper.class})
public interface StudentProfileMapper {

	StudentProfileDto toDto(StudentProfile studentProfile);

	Set<StudentProfileDto> toDtoList(List<StudentProfile> studentProfiles);

}
