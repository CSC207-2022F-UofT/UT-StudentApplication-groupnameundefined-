package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TimetableDto {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("blocks")
	private List<BlockDto> blocks;

}
