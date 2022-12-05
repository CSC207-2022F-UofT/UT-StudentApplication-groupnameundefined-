package backend.mappers;

import backend.dto.AptBlockDto;
import backend.dto.TimetableDto;
import backend.model.AptBlock;
import backend.model.Block;
import backend.model.Timetable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = {AptBlockMapper.class, SectionBlockMapper.class})
public interface TimetableMapper {

	TimetableDto toDto(Timetable timetable);

	List<TimetableDto> toDtoList(List<Timetable> timetables);

}
