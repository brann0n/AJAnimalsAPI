package com.brandon.animalapi.data;

import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.Owner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@Repository
@Transactional
public class AnimalRepository {

    @PersistenceContext(unitName = "ANIMALS")
    private EntityManager entityManager;

    /**
     * Creates an animal object
     *
     * @param animal the animal object to add to the datastorage
     * @return returns the Id of the created object
     */
    public long createAnimal(Animal animal) {
        entityManager.persist(animal);

        return animal.getId();
    }

    /**
     * Updates a specific Animal in the underlying hashmap
     *
     * @param animal id of the animal to update
     */
    public void updateAnimal(Animal animal) {
        Animal dbAnimal = entityManager.find(Animal.class, animal.getId());
        dbAnimal.setName(animal.getName());
        dbAnimal.setType(animal.getType());
        dbAnimal.setAge(animal.getAge());
        if((long) dbAnimal.getOwner().getId() != animal.getOwner().getId()){
            dbAnimal.setOwner(animal.getOwner());
        }
    }

    /**
     * Deletes an animal from the underlying hashmap
     *
     * @param id id of the animal to delete
     */
    public void deleteAnimal(long id) {
        Animal dbAnimal = entityManager.find(Animal.class, id);
        entityManager.remove(dbAnimal);
    }

    /**
     * Get all animals
     *
     * @return all animals in the repo
     */
    public Collection<Animal> getAnimals() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> query = cb.createQuery(Animal.class);
        return entityManager.createQuery(query.select(query.from(Animal.class))).getResultList();
    }

    /**
     * Get an animal by its id
     *
     * @param id animal Id
     * @return return the Animal Object
     */
    public Animal getAnimal(long id) {
        return entityManager.find(Animal.class, id);
    }
}
