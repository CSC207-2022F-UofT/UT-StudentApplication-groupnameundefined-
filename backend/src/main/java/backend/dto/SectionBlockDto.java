package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionBlockDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("repetition")
    private String repetition;

    @JsonProperty("repetitionTime")
    private String repetitionTime;

    @JsonProperty("startDay")
    private Integer startDay;

    @JsonProperty("startMil")
    private Integer startMil;

    @JsonProperty("endDay")
    private Integer endDay;

    @JsonProperty("endMil")
    private Integer endMil;

    @JsonProperty("sectionId")
    private Long sectionId;

}
