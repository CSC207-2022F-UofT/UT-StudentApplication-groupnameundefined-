package backend.mappers;

import backend.dto.HabitDto;
import backend.model.Habit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface HabitMapper {

	HabitDto toDto(Habit habit);

	List<HabitDto> toDtoList(List<Habit> habits);

}
