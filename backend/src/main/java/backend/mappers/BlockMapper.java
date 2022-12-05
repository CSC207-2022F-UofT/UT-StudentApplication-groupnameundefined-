package backend.mappers;

import backend.dto.BlockDto;
import backend.mappers.decorators.BlockMapperDecorator;
import backend.model.Block;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {AptBlockMapper.class, SectionBlockMapper.class})
@DecoratedWith(BlockMapperDecorator.class)
public interface BlockMapper {

	BlockDto toDto(Block block);

	List<BlockDto> toDtoList(List<Block> block);

}
