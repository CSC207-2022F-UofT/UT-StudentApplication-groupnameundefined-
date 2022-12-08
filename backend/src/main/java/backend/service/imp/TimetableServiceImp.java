package backend.service.imp;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import backend.exception.exceptions.InvalidInputException;
import backend.model.Block;
import backend.model.SectionBlock;
import backend.model.StudentProfile;
import backend.repository.SectionBlockRepository;
import backend.repository.StudentProfileRepository;
import backend.repository.TimetableRepository;
import backend.exception.exceptions.*;
import backend.service.StudentProfileService;
import net.fortuna.ical4j.data.ParserException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import backend.model.Timetable;
import backend.service.TimetableService;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;


import static java.util.Map.entry;

@Service
public class TimetableServiceImp implements TimetableService {

	private final Logger logger;

	private final TimetableRepository timetableRepository;

	private final SectionBlockRepository sectionBlockRepository;

	private final StudentProfileRepository studentProfileRepository;

	private final StudentProfileService studentProfileService;

	@Autowired
	public TimetableServiceImp(
			Logger logger,
			TimetableRepository timetableRepository,
			SectionBlockRepository sectionBlockRepository,
			StudentProfileRepository studentProfileRepository,
			StudentProfileService studentProfileService
	) {
		this.logger = logger;
		this.timetableRepository = timetableRepository;
		this.sectionBlockRepository = sectionBlockRepository;
		this.studentProfileRepository = studentProfileRepository;
		this.studentProfileService = studentProfileService;
	}

	@Override
	public List<Timetable> getAllTimetables() {
		return timetableRepository.findAll();
	}

	@Override
	public Timetable getTimetableById(Long id) {
		Optional<Timetable> timetable = timetableRepository.findById(id);
		if (timetable.isPresent()) {
			return timetable.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find timetable with id '%d'", id), Timetable.class);
	}

	@Override
	public List<Map<String, String>> parseIcs(MultipartFile file) {
		try {
			InputStream in = file.getInputStream();
			CalendarBuilder builder = new CalendarBuilder();
			Calendar calendar = builder.build(in);

			List<Map<String, String>> sectionData = new ArrayList<>();
			for (CalendarComponent vevent : calendar.getComponents("VEVENT")) {
				Optional<Property> _summary = vevent.getProperty(Property.SUMMARY);
				if (_summary.isPresent()) {
					String[] summary = _summary.get().getValue().split(" ");

					Map<String, String> sec = Map.ofEntries(entry("course", summary[0]), entry("section", summary[1]));
					sectionData.add(sec);
				}
			}
			return sectionData;
		} catch (IOException | ParserException e) {
			logger.error("error", e);
			throw new InvalidInputException("Unable to parse iCalendar file");
		}
	}

}
