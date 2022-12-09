package frontend.schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Component
public class BlockSchema {
    private Long id;
    private String type;
    private Integer startDay;
    private Integer startMil;
    private Integer endDay;
    private Integer endMil;
    private String repetition;
    private String repetitionTime;
    private Optional<SectionSchema> section;
    private Optional<String> location;
    private Optional<String> aptRequestId;

    public BlockSchema(){

    }
}