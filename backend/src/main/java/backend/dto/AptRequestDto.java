package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class AptRequestDto extends RequestDto {

	@JsonProperty("id")
	private Long id;

	@Column(name = "location")
	private String location;

	@JsonProperty("startDay")
	private Integer startDay;

	@JsonProperty("startMil")
	private Integer startMil;

	@JsonProperty("endDay")
	private Integer endDay;

	@JsonProperty("endMil")
	private Integer endMil;

	@JsonProperty("repetition")
	private String repetition;

	@JsonProperty("repetitionTime")
	private String repetitionTime;

}
