package backend.mappers;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.dto.SectionBlockDto;
import backend.model.SectionBlock;

@Mapper
public interface SectionBlockMapper {

    @Mapping(target = "repetitionTime", source = "repetitionTime")
    @Mapping(target = "sectionId", source = "section.id")
    SectionBlockDto toDto(SectionBlock sectionBlock);

    List<SectionBlockDto> toDtoList(List<SectionBlock> sectionBlocks);

}
