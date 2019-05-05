package be.appelicious.domain;

import javax.persistence.*;

@Entity
@Table(name = "go4fit_trainingtype")
public class TrainingsType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
