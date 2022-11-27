package backend.mappers;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.dto.SectionBlockDto;
import backend.model.SectionBlock;

@Mapper
public interface SectionBlockMapper {

    @Mapping(target = "sectionId", source = "section.id")
    SectionBlockDto sectionBlockToDto(SectionBlock sectionBlock);

    Set<SectionBlockDto> sectionBlocksToDtos(Set<SectionBlock> sectionBlocks);
}
