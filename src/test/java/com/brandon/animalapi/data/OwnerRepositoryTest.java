package com.brandon.animalapi.data;

import com.brandon.animalapi.TestApplicationContext;
import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestApplicationContext.class)
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @PersistenceContext(unitName = "ANIMALS")
    private EntityManager entityManager;


    @Test
    void createOwner() {
        Owner owner = new Owner("Steven", "Kazan", 5   );

        long createId = ownerRepository.createOwner(owner);

        assertThat(entityManager.find(Owner.class, createId)).isNotNull();
    }

    @Test
    void updateOwner() {
        Owner owner = ownerRepository.getOwner(1L);
        owner.setName("David____");

        ownerRepository.updateOwner(owner);

        assertThat(entityManager.find(Owner.class, 1L).getName()).isEqualTo(owner.getName());
    }

    @Test
    void removeOwner() {
        ownerRepository.removeOwner(3L);

        assertThat(entityManager.find(Owner.class, 3L)).isNull();
    }

    @Test
    void getOwners() {
    }

    @Test
    void getOwner() {
        Owner owner = ownerRepository.getOwner(1L);
        assertThat(owner).isNotNull();
    }
}
