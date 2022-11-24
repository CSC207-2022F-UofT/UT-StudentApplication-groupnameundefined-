package backend.model;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.Date;

public class Request {
    // status in {PENDING, APPROVED, DENIED}
    String status;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User from;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User to;
    String message;
    Timestamp time;
    final String defaultMessage = "Hi " + to.getName() + ", this is " + from.getName() + ", nice to meet you!";

    public Request(User from, User to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
        // Default status without explicitly addressing is PENDING
        this.status = "PENDING";
        // Keep track of the time when the request is created.
        Date date = new Date();
        this.time = new Timestamp(date.getTime());
    }

    public Request(User from, User to) {
        this.from = from;
        this.to = to;
        this.message = defaultMessage;
        // Default status without explicitly addressing is PENDING
        this.status = "PENDING";
        // Keep track of the time when the request is created.
        Date date = new Date();
        this.time = new Timestamp(date.getTime());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
