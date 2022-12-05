package backend.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitDto {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("mbti")
	private Integer mbti;

	@JsonProperty("talkative")
	private Integer talkative;

	@JsonProperty("collaborative")
	private Integer collaborative;

}
