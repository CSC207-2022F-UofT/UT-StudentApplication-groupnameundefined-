package backend.mappers;

import backend.dto.TimetableDto;
import backend.model.Timetable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {BlockMapper.class})
public interface TimetableMapper {

    @Mapping(target = "studentProfileId", source = "studentProfile.id")
    TimetableDto toDto(Timetable timetable);

    List<TimetableDto> toDtoList(List<Timetable> timetables);

}
