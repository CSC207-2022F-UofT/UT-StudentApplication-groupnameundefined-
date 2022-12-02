package backend.mappers;

import backend.dto.FriendRequestDto;
import backend.model.FriendRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface FriendRequestMapper {

	@Mapping(target = "fromId", source = "from.id")
	@Mapping(target = "toId", source = "to.id")
	FriendRequestDto toDto(FriendRequest friendRequest);

	List<FriendRequestDto> toDtoList(List<FriendRequest> friendRequests);

}
