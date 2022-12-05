package backend.mappers;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.dto.SectionBlockDto;
import backend.model.SectionBlock;

@Mapper(uses = {SectionMapper.class})
public interface SectionBlockMapper {

	SectionBlockDto toDto(SectionBlock sectionBlock);

	List<SectionBlockDto> toDtoList(List<SectionBlock> sectionBlocks);

}
