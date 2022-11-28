package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockDto {

    @JsonProperty("id")
    private Long id;

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
