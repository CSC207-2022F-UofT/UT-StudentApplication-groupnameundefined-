package backend.mappers;

import backend.dto.StudentProfileDto;
import backend.model.StudentProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {TimetableMapper.class, SocialMediaProfileMapper.class})
public interface StudentProfileMapper {

    @Mapping(target = "userId", source = "user.id")
    StudentProfileDto toDto(StudentProfile studentProfile);

    List<StudentProfileDto> toDtoList(List<StudentProfile> studentProfiles);

}
