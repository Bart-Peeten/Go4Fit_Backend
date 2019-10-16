package be.appelicious.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "go4fit_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "go4fit_reservation_customer",
            joinColumns = {@JoinColumn(name = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "customerId", nullable = false, updatable = false)})
    private List<User> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "go4fit_cancellation_customer",
            joinColumns = {@JoinColumn(name = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "customerId", nullable = false, updatable = false)})
    private List<User> cancelledUsers = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<User> getCancelledUsers() {
        return cancelledUsers;
    }

    public void setCancelledUsers(List<User> cancelledUsers) {
        this.cancelledUsers = cancelledUsers;
    }
}
