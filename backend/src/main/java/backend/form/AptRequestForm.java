package backend.form;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AptRequestForm {

    @Getter
    @Setter
    public static class CreateAptRequestForm {

        @NotNull
        private Long fromId;

        @NotNull
        private Long toId;

        @NotNull
        @Size(min = 0, max = 128, message = "Request message must not exceed 128 characters.")
        private String message;

        @NotNull
        @Size(min = 0, max = 64, message = "Request location must not exceed 128 characters.")
        private String location;

        @NotNull
        private Integer startDay;

        @NotNull
        private Integer startMil;

        @NotNull
        private Integer endDay;

        @NotNull
        private Integer endMil;

        @Pattern(regexp = "WEEKLY|BI_WEEKLY|YEARLY")
        private String repetition;

        @Pattern(regexp = "ONCE_A_WEEK|FIRST_AND_THIRD_WEEK|SECOND_AND_FOURTH_WEEK")
        private String repetitionTime;

        public CreateAptRequestForm(
                Long fromId,
                Long toId,
                String message,
                String location,
                Integer startDay,
                Integer startMil,
                Integer endDay,
                Integer endMil,
                String repetition,
                String repetitionTime
        ) {
            this.fromId = fromId;
            this.toId = toId;
            this.message = message;
            this.location = location;
            this.startDay = startDay;
            this.startMil = startMil;
            this.endDay = endDay;
            this.endMil = endMil;
            this.repetition = repetition;
            this.repetitionTime = repetitionTime;
        }
    }

    @Getter
    @Setter
    public static class UpdateAptRequestForm {

        @NotNull
        private Long id;

        @NotNull
        @Size(min = 0, max = 128, message = "Request message must not exceed 128 characters.")
        private String message;

        @NotNull
        @Size(min = 0, max = 64, message = "Request location must not exceed 128 characters.")
        private String location;

        @NotNull
        private Integer startDay;

        @NotNull
        private Integer startMil;

        @NotNull
        private Integer endDay;

        @NotNull
        private Integer endMil;

        @NotEmpty
        private String repetition;

        @NotEmpty
        private String repetitionTime;

        public UpdateAptRequestForm(
                Long id,
                String message,
                String location,
                Integer startDay,
                Integer startMil,
                Integer endDay,
                Integer endMil,
                String repetition,
                String repetitionTime
        ) {
            this.id = id;
            this.message = message;
            this.location = location;
            this.startDay = startDay;
            this.startMil = startMil;
            this.endDay = endDay;
            this.endMil = endMil;
            this.repetition = repetition;
            this.repetitionTime = repetitionTime;
        }
    }
}