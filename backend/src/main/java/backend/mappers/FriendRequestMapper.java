package backend.mappers;

import backend.dto.FriendRequestDto;
import backend.model.FriendRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {UserMapper.class})
public interface FriendRequestMapper {

	FriendRequestDto toDto(FriendRequest friendRequest);

	List<FriendRequestDto> toDtoList(List<FriendRequest> friendRequests);

}
