package backend.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("courseId")
    private Long courseId;

    @JsonProperty("sectionBlocks")
    private Set<SectionBlockDto> sectionBlocks;

}
