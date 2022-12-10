package backend.controller.imp;

import java.util.List;

import backend.dto.TimetableDto;
import backend.mappers.TimetableMapper;
import backend.model.Timetable;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import backend.controller.TimetableController;
import backend.service.TimetableService;
import org.springframework.stereotype.Controller;

@Controller
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
	public ResponseEntity<List<TimetableDto>> getAllTimetables() {
		List<Timetable> timetables = timetableService.getAllTimetables();
		List<TimetableDto> timetableDtos = timetableMapper.toDtoList(timetables);

		return new ResponseEntity<>(timetableDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TimetableDto> getTimetableById(Long id) {
		Timetable timetable = timetableService.getTimetableById(id);
		TimetableDto timetableDto = timetableMapper.toDto(timetable);

		return new ResponseEntity<>(timetableDto, HttpStatus.OK);
	}

}
