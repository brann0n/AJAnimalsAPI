package com.brandon.animalapi.data;

import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.Owner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;

@Component
public class OwnerRepository {

    /**
     * Local object to store all animals in.
     */
    private final HashMap<Integer, Owner> owners;

    /**
     * private index to be kept unique over any table changes
     */
    private int autoIncrementIndex = 1;

    public OwnerRepository() {
        this.owners = new HashMap<>();

        initRepository();
    }

    /**
     * Function that adds a preset of objects to the database with the index kept intact
     */
    private void initRepository() {
        createOwner(new Owner("John Doe", "small street 3944b, BigCity", false, 2));
        createOwner(new Owner("Ben Al", "big street 24, SmallTown", true, 8));
    }

    /**
     * Creates an owner in the underlying hashmap
     *
     * @param owner
     * @return
     */
    public int createOwner(Owner owner) {
        int createIndex = autoIncrementIndex++;
        owner.setId(createIndex);
        this.owners.put(createIndex, owner);
        return createIndex;
    }

    /**
     * Updates an owner object in the underlying hashmap
     *
     * @param owner
     * @return success
     */
    public void updateOwner(Owner owner) {
        if (!this.owners.containsKey(owner.getId())) throw new DataNotFoundException("Owners");
        owners.put(owner.getId(), owner);
    }

    /**
     * Deletes an owner from the underlying hashmap
     *
     * @param id
     * @return success
     */
    public void removeOwner(int id) {
        if (!this.owners.containsKey(id)) throw new DataNotFoundException("Owners", id);
        owners.remove(id);
    }

    /**
     * Gets all owners
     *
     * @return all Owners: {@code Collection<Owner>}
     */
    public Collection<Owner> getOwners() {
        return owners.values();
    }

    /**
     * Gets a specific {@code Owner}
     *
     * @param id id of the owner to get
     * @return returns a {@code Owner}
     */
    public Owner getOwner(int id) {
        if (!this.owners.containsKey(id)) throw new DataNotFoundException("Owners", id);
        return owners.get(id);
    }
}
