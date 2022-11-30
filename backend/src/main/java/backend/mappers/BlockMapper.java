package backend.mappers;

import backend.dto.BlockDto;
import backend.model.Block;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BlockMapper {

    BlockDto toDto(Block block);

    List<BlockDto> toDtoList(List<Block> block);

}
