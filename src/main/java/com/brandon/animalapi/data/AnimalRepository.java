package com.brandon.animalapi.data;

import com.brandon.animalapi.models.Animal;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;

@Component
public class AnimalRepository {

    /**
     * Local object to store all animals in.
     */
    private final HashMap<Integer, Animal> animals;

    /**
     * private index to be kept unique over any table changes
     */
    private int autoIncrementIndex = 1;

    public AnimalRepository() {
        this.animals = new HashMap<>();

        initRepository();
    }

    /**
     * Function that adds a preset of objects to the database with the index kept intact
     */
    private void initRepository() {
        createAnimal(new Animal("Fluffy", "Dog", 4, 1));
        createAnimal(new Animal("Boomer", "Dog", 2, 1));
        createAnimal(new Animal("Mr. Biggles", "Cat", 7, 2));
    }

    /**
     * Creates an animal object
     *
     * @param animal the animal object to add to the datastorage
     * @return returns the Id of the created object
     */
    public int createAnimal(Animal animal) {
        int createIndex = autoIncrementIndex++;
        animal.setId(createIndex);
        this.animals.put(createIndex, animal);
        return createIndex;
    }

    /**
     * Updates a specific Animal in the underlying hashmap
     *
     * @param animal id of the animal to update
     */
    public void updateAnimal(Animal animal) {
        if (!this.animals.containsKey(animal.getId())) throw new DataNotFoundException("Animals");
        animals.put(animal.getId(), animal);
    }

    /**
     * Deletes an animal from the underlying hashmap
     *
     * @param id id of the animal to delete
     */
    public void deleteAnimal(int id) {
        if (!this.animals.containsKey(id)) throw new DataNotFoundException("Animals", id);
        animals.remove(id);
    }

    /**
     * Get all animals
     *
     * @return all animals in the repo
     */
    public Collection<Animal> getAnimals() {
        return this.animals.values();
    }

    /**
     * Get an animal by its id
     *
     * @param id animal Id
     * @return return the Animal Object
     */
    public Animal getAnimal(int id) {
        if (!this.animals.containsKey(id)) throw new DataNotFoundException("Animals", id);
        return this.animals.get(id);
    }
}
