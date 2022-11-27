package backend.form;

import java.io.File;

public class TimetableForm {

    public static class ParseIcsForm {

        ParseIcsForm() {
        }

        private File file;

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

    }

}