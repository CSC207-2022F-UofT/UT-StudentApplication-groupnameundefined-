package backend.mappers;

import backend.dto.AptRequestDto;
import backend.model.AptRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AptRequestMapper {

	@Mapping(target = "fromId", source = "from.id")
	@Mapping(target = "toId", source = "to.id")
	AptRequestDto toDto(AptRequest aptRequest);

	List<AptRequestDto> toDtoList(List<AptRequest> aptRequests);

}
