package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AptRequestDto extends RequestDto {

	@JsonProperty("aptBlock")
	private AptBlockDto aptBlock;

}
