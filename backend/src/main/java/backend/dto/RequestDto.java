package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RequestDto {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("status")
	private String status;

	@JsonProperty("fromId")
	private Long fromId;

	@JsonProperty("toId")
	private Long toId;

	@JsonProperty("message")
	private String message;

	@JsonProperty("timestamp")
	private Timestamp timestamp;
	
}