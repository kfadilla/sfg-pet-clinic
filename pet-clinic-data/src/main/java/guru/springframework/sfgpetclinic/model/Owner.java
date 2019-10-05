package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
public class Owner extends Person {

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();


    @Builder

    public Owner(Long id, String lastName, String firstName, String address,
                 String city, String phone, Set<Pet> pets) {
        super(id, lastName, firstName);
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.pets = pets;
    }
}
