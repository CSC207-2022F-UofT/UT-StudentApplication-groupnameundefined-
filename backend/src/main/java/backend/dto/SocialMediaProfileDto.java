package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialMediaProfileDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("instagram_id")
    private String instagramId;

    @JsonProperty("facebook_id")
    private String facebookId;

    @JsonProperty("studentProfileId")
    private Long studentProfileId;

}
