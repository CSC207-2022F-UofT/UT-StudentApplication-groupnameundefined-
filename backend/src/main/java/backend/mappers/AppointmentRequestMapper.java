package backend.mappers;

import backend.dto.AppointmentRequestDto;
import backend.dto.FriendRequestDto;
import backend.model.AppointmentRequest;
import backend.model.FriendRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AppointmentRequestMapper {

	@Mapping(target = "fromId", source = "from.id")
	@Mapping(target = "toId", source = "to.id")
	AppointmentRequestDto toDto(AppointmentRequest appointmentRequest);

	List<AppointmentRequestDto> toDtoList(List<AppointmentRequest> appointmentRequests);

}
