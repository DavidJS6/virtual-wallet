package soe.mdeis.modulo5.virtualwallet.database.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "log_events", schema = "virtual-wallet")
public class LogEvent {
    private Integer id;
    private Timestamp date;
    private String event;
    private User user;

    public LogEvent() {
    }

    public LogEvent(Timestamp date, String event, User user) {
        this.date = date;
        this.event = event;
        this.user = user;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "event")
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEvent logEvent = (LogEvent) o;
        return Objects.equals(id, logEvent.id) &&
                Objects.equals(date, logEvent.date) &&
                Objects.equals(event, logEvent.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, event);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
