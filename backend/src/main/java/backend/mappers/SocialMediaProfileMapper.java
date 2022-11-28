package backend.mappers;

import backend.dto.SocialMediaProfileDto;
import backend.model.SocialMediaProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SocialMediaProfileMapper {

    @Mapping(target = "studentProfileId", source = "studentProfile.id")
    SocialMediaProfileDto socialMediaProfileToDto(SocialMediaProfile socialMediaProfile);

}
