package backend.model;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name="Appointments")
public class AppointmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aptId;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "location")
    private String location;

    @Column(name = "invitee")
    private User invitee;

    @Column(name = "attendee")
    private User attendee;
    @Column(name = "status")
    private String status;
    public AppointmentRequest() {}

    public AppointmentRequest(User invitee, User attendee, Timestamp time, String location) {
            this.invitee = invitee;
            this.attendee = attendee;
            this.time = time;
            this.location = location;
            this.status = "WAITING";

        }

    public Long getAptId() {
            return aptId;
        }
    public String getLocation() {
        return location;
    }

    public Timestamp getTime() {
        return time;
    }

    public User getInvitee() {
        return invitee;
    }

    public User getAttendee() {
        return attendee;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAttendee(User attendee) {
        this.attendee = attendee;
    }

    public void setInvitee(User invitee) {
        this.invitee = invitee;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
