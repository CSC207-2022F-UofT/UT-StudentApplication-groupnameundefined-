package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitVisibilityDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("habitId")
    private Long habitId;

    @JsonProperty("mbti")
    private Boolean mbti;

    @JsonProperty("talkative")
    private Boolean talkative;

    @JsonProperty("collaborative")
    private Boolean collaborative;

}
