package backend.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("sectionCode")
    private String sectionCode;

    @JsonProperty("campus")
    private String campus;

    @JsonProperty("sections")
    private Set<SectionDto> sections;

}
