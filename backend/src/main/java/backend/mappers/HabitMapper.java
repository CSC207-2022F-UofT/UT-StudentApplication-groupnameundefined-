package backend.mappers;

import backend.dto.HabitDto;
import backend.model.Habit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {HabitVisibilityMapper.class})
public interface HabitMapper {

    @Mapping(target = "studentProfileId", source = "studentProfile.id")
    HabitDto toDto(Habit habit);

    List<HabitDto> toDtoList(List<Habit> habits);

}
