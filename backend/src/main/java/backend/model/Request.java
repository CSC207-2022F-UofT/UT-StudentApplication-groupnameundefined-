package backend.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // status in {PENDING, APPROVED, DENIED}
    @Column(name = "status")
    private String status;
    @OneToOne
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    private User from;
    @OneToOne
    @JoinColumn(name = "to_id", referencedColumnName = "id")
    private User to;
    @Column(name = "message")
    private String message;

    @Column(name = "time")
    private Timestamp time;
    final String defaultMessage = "Hi " + to.getName() + ", this is " + from.getName() + ", nice to meet you!";

    public Request() {}
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

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        String s = "Request " + this.id + ": from " + this.from.getName()
                + "to " + this.to.getName() + " is " + this.status;
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        Request request = (Request) o;
        return this.id.equals(request.id);
    }
}
