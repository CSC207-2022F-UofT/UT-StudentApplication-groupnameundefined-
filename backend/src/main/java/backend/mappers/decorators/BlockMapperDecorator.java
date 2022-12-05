package backend.mappers.decorators;

import backend.dto.BlockDto;
import backend.mappers.AptBlockMapper;
import backend.mappers.BlockMapper;
import backend.mappers.SectionBlockMapper;
import backend.model.AptBlock;
import backend.model.Block;
import backend.model.SectionBlock;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BlockMapperDecorator implements BlockMapper {

	@Autowired
	private BlockMapper delegate;

	@Override
	public BlockDto toDto(Block block) {
		BlockDto blockDto = null;

		if (block instanceof AptBlock) {
			blockDto = Mappers.getMapper(AptBlockMapper.class).toDto((AptBlock) block);
		} else if (block instanceof SectionBlock) {
			blockDto = Mappers.getMapper(SectionBlockMapper.class).toDto((SectionBlock) block);
		} else {
			blockDto = this.delegate.toDto(block);
		}

		return blockDto;
	}
}
