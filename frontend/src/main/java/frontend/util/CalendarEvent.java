package frontend.util;

import lombok.Getter;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class CalendarEvent {

    private LocalDate date;
    private DayOfWeek dayOfTheWeek;
    private LocalTime start;
    private LocalTime end;
    private String text;
    private Color color;

    public static LocalTime milliToTime(int milliseconds){
        int seconds = (milliseconds / 1000) % 60 ;
        int minutes = ((milliseconds / (1000*60)) % 60);
        int hours   = ((milliseconds / (1000*60*60)) % 24);
        return LocalTime.of(hours, minutes, seconds);
    }

    public CalendarEvent(Integer dayOfWeek, Integer start, Integer end, String text, Color color){
        this.dayOfTheWeek = DayOfWeek.of(dayOfWeek);
        this.start = milliToTime(start);
        this.end = milliToTime(end);
        this.text = text;
        this.color = color;
        int dayDiff = Math.abs(dayOfWeek - LocalDate.now().getDayOfWeek().getValue());
        if (dayDiff > 0){
            this.date = LocalDate.now().plusDays(dayDiff);
        }
        else if (dayDiff < 0){
            this.date = LocalDate.now().minusDays(dayDiff);
        }
        else{
            this.date = LocalDate.now();
        }
    }

    public CalendarEvent(LocalDate date, LocalTime start, LocalTime end, String text, Color color) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.text = text;
        this.color = color;
    }

    public String toString() {
        return getDate() + " " + getDayOfTheWeek() + " " + getStart() + "-" + getEnd() + ". " + getText();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEvent that = (CalendarEvent) o;

        if (!date.equals(that.date)) return false;
        if (!start.equals(that.start)) return false;
        return end.equals(that.end);

    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
