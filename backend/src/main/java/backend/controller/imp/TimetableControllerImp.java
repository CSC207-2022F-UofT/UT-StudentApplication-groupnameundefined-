package backend.controller.imp;

import java.io.File;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.controller.TimetableController;
import backend.form.TimetableForm.*;
import backend.model.Block;
import backend.service.TimetableService;

@CrossOrigin
@RestController
@RequestMapping("/api/timetable")
public class TimetableControllerImp implements TimetableController {

    @Autowired
    TimetableService timetableService;

    @Override
    @PostMapping("/parse-ics")
    public ResponseEntity<Set<Block>> parseIcs(@RequestParam("file") MultipartFile file) {
        Set<Block> blocks = timetableService.parseIcs(file);

        return new ResponseEntity<Set<Block>>(blocks, HttpStatus.OK);
    }
}
