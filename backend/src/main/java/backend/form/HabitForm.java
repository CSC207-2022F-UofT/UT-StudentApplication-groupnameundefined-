package backend.form;

import java.io.File;

import javax.validation.constraints.*;

import backend.model.StudentProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class HabitForm {

    @Getter
    public static class CreateHabitForm {

        @NotNull
        private Long studentProfileId;

        @NotNull
        @Max(5)
        @Min(1)
        private Integer mbti;

        @NotNull
        @Max(5)
        @Max(1)
        private Integer talkative;

        @NotNull
        @Max(5)
        @Min(1)
        private Integer collaborative;

        @NotNull
        private Boolean mbtiVisibility;

        @NotNull
        private Boolean talkativeVisibility;

        @NotNull
        private Boolean collaborativeVisibility;

    }

}
