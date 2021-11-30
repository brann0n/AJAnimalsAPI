package com.brandon.animalapi.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "owner")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "familiy_size", nullable = false)
    private Integer familiySize;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Animal> animalList = new ArrayList<>(0);

    public Owner(String name, String address, int familiySize){
        this.name = name;
        this.address = address;
        this.familiySize = familiySize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Owner owner = (Owner) o;
        return id != null && Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}