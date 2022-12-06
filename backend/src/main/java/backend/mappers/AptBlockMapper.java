package backend.mappers;

import backend.dto.AptBlockDto;
import backend.dto.SectionBlockDto;
import backend.model.AptBlock;
import backend.model.SectionBlock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {SectionMapper.class, AptRequestMapper.class})
public interface AptBlockMapper {

	@Mapping(source = "aptRequest.id", target = "aptRequestId")
	AptBlockDto toDto(AptBlock aptBlock);

	List<AptBlockDto> toDtoList(List<AptBlock> aptBlocks);

}
