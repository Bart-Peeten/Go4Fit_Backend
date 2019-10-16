package be.appelicious.Helpers;

import be.appelicious.domain.Reservation;
import be.appelicious.domain.User;
import be.appelicious.interfaces.CrudHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class CrudHelperImpl implements CrudHelper {

    public boolean isAlreadyReserved(User item, Reservation reservationResult) {
        AtomicBoolean isReserved = new AtomicBoolean(false);
        reservationResult.getUsers().forEach(user -> {
            if (user.getEmail().equals(item.getEmail())) {
                isReserved.set(true);
            }
        });
        return isReserved.get();
    }

    public String[][] filltimes() {
        String tuesday[] = {"19:00", "20:00"};
        String wednesday[] = {"09:00", "19:00", "20:00"};
        String thursday[] = {"19:00"};
        String sunday[] = {"08:00", "09:00", "10:00"};

        String[][] tmpArray = new String[][]{tuesday, wednesday, thursday, sunday};

        return tmpArray;
    }

    public List<String> parseFullNames(List<Reservation> result) {
        List<String> tmpList = new ArrayList<>();
        result.forEach(item -> {
            List<User> userList = item.getUsers();
            userList.forEach(user -> {
                String fullName = user.getFirstName() + " " + user.getLastName();
                tmpList.add(fullName);
            });
        });

        return tmpList;
    }

    public List<String> parseCancellationFullNames(List<Reservation> result) {
        List<String> tmpList = new ArrayList<>();
        result.forEach(item -> {
            List<User> userList = item.getCancelledUsers();
            userList.forEach(user -> {
                String fullName = user.getFirstName() + " " + user.getLastName();
                tmpList.add(fullName);
            });
        });

        return tmpList;
    }
}
