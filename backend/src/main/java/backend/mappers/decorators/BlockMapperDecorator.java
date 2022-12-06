package backend.mappers.decorators;

import backend.dto.BlockDto;
import backend.mappers.AptBlockMapper;
import backend.mappers.BlockMapper;
import backend.mappers.SectionBlockMapper;
import backend.model.AptBlock;
import backend.model.Block;
import backend.model.SectionBlock;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class BlockMapperDecorator implements BlockMapper {

	@Autowired
	Logger logger;

	@Autowired
	@Qualifier("delegate")
	private BlockMapper delegate;

	@Autowired
	private AptBlockMapper aptBlockMapper;

	@Autowired
	private SectionBlockMapper sectionBlockMapper;

	@Override
	public BlockDto toDto(Block block) {
		BlockDto blockDto = null;

		if (block instanceof AptBlock) {
			blockDto = aptBlockMapper.toDto((AptBlock) block);
		} else if (block instanceof SectionBlock) {
			logger.info("Is Section Block");
			blockDto = sectionBlockMapper.toDto((SectionBlock) block);
		} else {
			blockDto = this.delegate.toDto(block);
		}

		return blockDto;
	}
}
