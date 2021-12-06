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
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestApplicationContext.class)
public class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository animalRepository;

    @PersistenceContext(unitName = "ANIMALS")
    private EntityManager entityManager;

    @Test
    public void testGetAllAnimals() {
        Collection<Animal> animals = animalRepository.getAnimals();
        assertThat(animals).isNotEmpty();
    }

    @Test
    public void testGetAnimal() {
        Animal animal = animalRepository.getAnimal(1);
        assertThat(animal).isNotNull();
        assertThat(animal.getOwner().getName()).isEqualTo("Hans");
    }

    @Test
    public void testCreateAnimal() {
        Animal animal = new Animal("Bas", "waakhond", 400);
        animal.setOwner(entityManager.getReference(Owner.class, 1L));
        long id = animalRepository.createAnimal(animal);
        assertThat(animal.getId()).isNotEqualTo(0).isEqualTo(id);
    }

    @Test
    public void testUpdateAnimal() {
        //get the object that we will be changing
        Animal animal = animalRepository.getAnimal(1);
        animal.setName("Trap"); //stairs -> trap

        //get an unchanged object
        Animal animal2 = entityManager.find(Animal.class, 1L);

        //check that the new database object does not reflect the new change yet
        assertThat(animal.getName()).isNotEqualTo(animal2.getName());

        //do the update
        animalRepository.updateAnimal(animal);

        //update the unchanged object
        animal2 = entityManager.find(Animal.class, 1L);

        //check if the update changed the name property
        assertThat(animal.getName()).isEqualTo(animal2.getName());
    }

    @Test
    public void testUpdateAnimalOwner(){
        Animal animal = animalRepository.getAnimal(1);

        Animal animal2 = entityManager.find(Animal.class, 1L);

        assertThat(animal.getOwner()).isEqualTo(animal2.getOwner());

        animal.setOwner(entityManager.find(Owner.class, 2L));
        animalRepository.updateAnimal(animal);

        animal2 = entityManager.find(Animal.class, 1L);
        assertThat(animal.getOwner()).isEqualTo(animal2.getOwner());
    }

    @Test
    public void testDeleteAnimal(){
        animalRepository.deleteAnimal(2L);
        assertThat(entityManager.find(Animal.class, 2L)).isNull();
    }
}