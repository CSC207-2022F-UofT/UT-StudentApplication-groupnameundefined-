package backend.dto;

import backend.model.User;
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

	@JsonProperty("aptRequestFromName")
	private String aptRequestFromName;

	@JsonProperty("aptRequestToName")
	private String aptRequestToName;


}
