package backend.mappers;

import backend.dto.HabitVisibilityDto;
import backend.model.HabitVisibility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface HabitVisibilityMapper {

    @Mapping(target = "habitId", source = "habit.id")
    HabitVisibilityDto toDto(HabitVisibility habitVisibility);

    List<HabitVisibilityDto> toDtoList(List<HabitVisibility> habitVisibilities);

}
