package com.brandon.animalapi.data;

import com.brandon.animalapi.TestApplicationContext;
import com.brandon.animalapi.models.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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


    }

    @Test
    void updateOwner() {
    }

    @Test
    void removeOwner() {
    }

    @Test
    void getOwners() {
    }

    @Test
    void getOwner() {
    }
}
