package guru.springframework.sfgpetclinic.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity{

    public Person(Long id, String lastName, String firstName) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
    }


    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

}
