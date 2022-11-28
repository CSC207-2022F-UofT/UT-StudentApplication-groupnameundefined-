package backend.mappers;

import backend.dto.TimetableDto;
import backend.model.Timetable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { BlockMapper.class })
public interface TimetableMapper {

    @Mapping(target = "studentProfileId", source = "studentProfile.id")
    TimetableDto timetableToDto(Timetable timetable);

}
