package backend.service.imp;

import java.io.InputStream;
import java.util.*;

import backend.model.Block;
import backend.model.SectionBlock;
import backend.model.StudentProfile;
import backend.repository.SectionBlockRepository;
import backend.repository.StudentProfileRepository;
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

import javax.persistence.EntityNotFoundException;

import static java.util.Map.entry;

@Service
public class TimetableServiceImp implements TimetableService {

    @Autowired
    Logger logger;

    @Autowired
    SectionBlockRepository sectionBlockRepository;

    @Autowired
    StudentProfileRepository studentProfileRepository;

    @Override
    public List<Map<String, String>> parseIcs(MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(in);

            List<Map<String, String>> sectionData = new ArrayList<>();
            for (CalendarComponent vevent : calendar.getComponents("VEVENT")) {
                // String dtstart = vevent.getProperty(Property.DTSTART).get().getValue();
                // String dtend = vevent.getProperty(Property.DTEND).get().getValue();

                // DateTimeFormatter formatter =
                // DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");

                // LocalDateTime ldtstart = LocalDateTime.parse(dtstart, formatter);
                // LocalDateTime ldtend = LocalDateTime.parse(dtend, formatter);

                // ZoneId zoneId = ZoneId.of("GMT-05:00");

                // ZonedDateTime zdtstart = ldtstart.atZone(zoneId);
                // ZonedDateTime zdtend = ldtend.atZone(zoneId);

                // Integer startDay = zdtstart.getDayOfWeek().getValue();
                // Integer startMil = zdtstart.toLocalTime().toSecondOfDay() * 1000;
                // Integer endDay = zdtend.getDayOfWeek().getValue();
                // Integer endMil = zdtend.toLocalTime().toSecondOfDay() * 1000;

                String[] summary = vevent.getProperty(Property.SUMMARY).get().getValue().split(" ");

                Map<String, String> sec = Map.ofEntries(entry("course", summary[0]), entry("section", summary[1]));
                sectionData.add(sec);
            }

            return sectionData;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }

    }

    @Override
    public Timetable createTimetable(Timetable timetable) {
        return null;
    }

    @Override
    public Timetable createTimetable(StudentProfile studentProfile, Set<Block> blocks) {
        Timetable timetable = new Timetable();

        timetable.setStudentProfile(studentProfile);
        timetable.setBlocks(blocks);

        return timetable;
    }

    @Override
    public Timetable createTimetable(Long studentProfileId, MultipartFile iCalendar) {
        Optional<StudentProfile> _studentProfile = studentProfileRepository.findById(studentProfileId);

        StudentProfile studentProfile;
        if (_studentProfile.isPresent()) {
            studentProfile = _studentProfile.get();
        } else {
            throw new EntityNotFoundException();
        }

        List<Map<String, String>> sectionData = this.parseIcs(iCalendar);

        Set<Block> sectionBlocks = new HashSet<>();
        for (Map<String, String> sec : sectionData) {
            List<SectionBlock> _sectionBlocks = sectionBlockRepository.findByCode(sec.get("course"), sec.get("section"));
            sectionBlocks.addAll(_sectionBlocks);
        }

        return this.createTimetable(studentProfile, sectionBlocks);

    }

}
