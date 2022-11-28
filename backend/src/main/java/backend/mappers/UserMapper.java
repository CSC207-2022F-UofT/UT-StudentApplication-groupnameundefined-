package backend.mappers;

import backend.dto.UserDto;
import backend.model.User;
import org.mapstruct.Mapper;

@Mapper(uses = { StudentProfileMapper.class, TimetableMapper.class, BlockMapper.class })
public interface UserMapper {

    UserDto userToDto(User user);

}
