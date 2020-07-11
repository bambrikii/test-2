package org.bambrikii.examples.batch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "people")
public class People {
    @Id
    @SequenceGenerator(name = "people_seq", sequenceName = "people_seq", initialValue = 5, allocationSize = 1)
    @GeneratedValue(generator = "people_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
