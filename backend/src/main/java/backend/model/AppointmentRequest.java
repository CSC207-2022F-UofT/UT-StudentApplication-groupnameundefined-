package backend.model;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name="Appointments")
public class AppointmentRequest extends Request {
    @Column(name = "location")
    private String location;

    @Column(name = "aptTime")
    private Timestamp aptTime;
    public AppointmentRequest() {
        super();
        this.aptTime =  aptTime;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public Timestamp getAptTime() {
        return aptTime;
    }

    public void setAptTime(Timestamp aptTime) {
        this.aptTime = aptTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
