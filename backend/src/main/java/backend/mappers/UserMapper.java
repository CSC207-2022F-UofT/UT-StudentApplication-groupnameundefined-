package backend.mappers;

import backend.dto.UserDto;
import backend.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {StudentProfileMapper.class, TimetableMapper.class, BlockMapper.class, FriendRequestMapper.class})
public interface UserMapper {

	UserDto toDto(User user);

	List<UserDto> toDtoList(List<User> users);

}
