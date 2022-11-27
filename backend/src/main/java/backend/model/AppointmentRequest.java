package backend.model;

import javax.persistence.*;
import java.sql.Timestamp;

public class AppointmentRequest {
    @Entity
    @Table(name="Appointments")
    public class Appointment {

        public Appointment() {}

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
    }
}
