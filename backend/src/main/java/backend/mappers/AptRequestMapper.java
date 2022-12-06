package backend.mappers;

import backend.dto.AptRequestDto;
import backend.model.AptBlock;
import backend.model.AptRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {AptBlockMapper.class, UserMapper.class})
public interface AptRequestMapper {

	AptRequestDto toDto(AptRequest aptRequest);

	List<AptRequestDto> toDtoList(List<AptRequest> aptRequests);

}
