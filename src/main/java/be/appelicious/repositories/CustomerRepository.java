package be.appelicious.repositories;

import be.appelicious.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByFirstNameAndLastName(String firstname, String lastname);
    int deleteByFirstNameAndLastName(String firstname, String lastname);
}
