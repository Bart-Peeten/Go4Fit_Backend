package be.appelicious.repositories;

import be.appelicious.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

@Repository
public interface CustomerRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
