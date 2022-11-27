package backend.controller;

import java.io.File;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import backend.form.TimetableForm.ParseIcsForm;
import backend.model.Block;

public interface TimetableController {

    ResponseEntity<Set<Block>> parseIcs(MultipartFile file);

}
