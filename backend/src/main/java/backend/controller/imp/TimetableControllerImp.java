package backend.controller.imp;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import backend.dto.TimetableDto;
import backend.mappers.TimetableMapper;
import backend.model.Timetable;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import backend.controller.TimetableController;
import backend.form.TimetableForm.*;
import backend.model.Block;
import backend.service.TimetableService;

@CrossOrigin
@RestController
@RequestMapping("/api/timetable")
public class TimetableControllerImp implements TimetableController {

	private final Logger logger;

	private final TimetableService timetableService;
	private final TimetableMapper timetableMapper;

	@Autowired
	public TimetableControllerImp(
			Logger logger,
			TimetableService timetableService,
			TimetableMapper timetableMapper
	) {
		this.logger = logger;
		this.timetableService = timetableService;
		this.timetableMapper = timetableMapper;
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<List<TimetableDto>> getAllTimetables() {
		List<Timetable> timetables = timetableService.getAllTimetables();
		List<TimetableDto> timetableDtos = timetableMapper.toDtoList(timetables);

		return new ResponseEntity<>(timetableDtos, HttpStatus.OK);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<TimetableDto> getTimetableById(@PathVariable Long id) {
		Timetable timetable = timetableService.getTimetableById(id);
		TimetableDto timetableDto = timetableMapper.toDto(timetable);

		return new ResponseEntity<>(timetableDto, HttpStatus.OK);
	}

}
