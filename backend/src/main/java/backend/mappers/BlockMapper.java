package backend.mappers;

import backend.dto.BlockDto;
import backend.model.Block;
import org.mapstruct.Mapper;

@Mapper
public interface BlockMapper {

    BlockDto blockToDto(Block block);

}
