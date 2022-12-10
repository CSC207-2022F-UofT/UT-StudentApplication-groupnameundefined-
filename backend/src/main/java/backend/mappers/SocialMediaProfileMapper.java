package backend.mappers;

import backend.dto.SocialMediaProfileDto;
import backend.model.SocialMediaProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SocialMediaProfileMapper {

	SocialMediaProfileDto toDto(SocialMediaProfile socialMediaProfile);

	List<SocialMediaProfileDto> toDtoList(List<SocialMediaProfile> socialMediaProfiles);

}
