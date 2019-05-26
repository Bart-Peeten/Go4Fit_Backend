package be.appelicious.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GetAspect {

    @Before(value = "execution(* be.appelicious.controllers.*.get*(..))")
    public void before(){
        System.out.println("Before executing GET method.");
    }

    @Before(value = "execution(* be.appelicious.controllers.*.getReservationByLoginName(..)) && args(name)")
    public void before(String name){
        System.out.println("Login name passed to method is: " + name);
    }

    @After(value = "execution(* be.appelicious.services.*.getNamesFromReservationByDateAndTime(..))")
    public void after(){System.out.println("Function called after service method");
    }

    @Before(value = "execution(* be.appelicious.controllers.*.getReservationByFirstAndLastName(..))" +
            " && args(firstname, lastname)", argNames = "firstname,lastname")
    public void before(String firstname, String lastname){
        System.out.println("Firstname passed to method is: " +
                            firstname + " and Lastname is: " +
                             lastname);
    }

}
