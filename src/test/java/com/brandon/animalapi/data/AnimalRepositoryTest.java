package com.brandon.animalapi.data;

import com.brandon.animalapi.TestApplicationContext;
import com.brandon.animalapi.models.Animal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestApplicationContext.class)
public class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void testGetAllAnimals(){
        Collection<Animal> animals = animalRepository.getAnimals();
        assertThat(animals).isNotEmpty();
    }
}