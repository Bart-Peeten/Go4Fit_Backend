package be.appelicious.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "go4fit_not_allowed_reservation")
public class NotAllowedReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notAllowedId")
    private long notAllowedId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "go4fit_not_allowed_reservation_customer",
            joinColumns = {@JoinColumn(name = "notAllowedId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "customerId", nullable = false, updatable = false)})
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public long getNotAllowedId() {
        return notAllowedId;
    }

    public void setNotAllowedId(long notAllowedId) {
        this.notAllowedId = notAllowedId;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
