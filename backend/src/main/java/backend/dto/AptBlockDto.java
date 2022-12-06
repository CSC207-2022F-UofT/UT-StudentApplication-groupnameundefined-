package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AptBlockDto extends BlockDto {

	@JsonProperty("location")
	private String location;

	@JsonProperty("aptRequestId")
	private Long aptRequestId;
	
}
