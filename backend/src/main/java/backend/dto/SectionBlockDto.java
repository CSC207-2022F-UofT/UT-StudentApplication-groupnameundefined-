package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionBlockDto extends BlockDto {

	@JsonProperty("section")
	private SectionDto section;
	
}
