package backend.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import backend.model.Block;
import backend.model.Timetable;
import backend.service.TimetableService;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VEvent;

@Service
public class TimetableServiceImp implements TimetableService {

    @Autowired
    Logger logger;

    @Override
    public Set<Block> parseIcs(MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(in);
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

                String code = summary[0];
                String section = summary[1];
            }
            return null;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }

    }

    @Override
    public Timetable createTimetable(Timetable timetable) {
        return null;
    }

}
