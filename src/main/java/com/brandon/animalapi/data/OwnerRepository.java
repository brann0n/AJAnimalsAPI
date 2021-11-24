package com.brandon.animalapi.data;

import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.Owner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public class OwnerRepository {

    @PersistenceContext(unitName = "ANIMALS")
    private EntityManager entityManager;

    /**
     * Creates an owner in the underlying hashmap
     *
     * @param owner
     * @return
     */
    @Transactional
    public Long createOwner(Owner owner) {
        entityManager.persist(owner);
        return owner.getId();
    }

    /**
     * Updates an owner object in the underlying hashmap
     *
     * @param owner
     * @return success
     */
    @Transactional
    public void updateOwner(Owner owner) {
        Owner dbOwner = entityManager.find(Owner.class, owner.getId());
        dbOwner.setAddress(owner.getAddress());
        dbOwner.setName(owner.getName());
        dbOwner.setFamiliySize(owner.getFamiliySize());
    }

    /**
     * Deletes an owner from the underlying hashmap
     *
     * @param id
     * @return success
     */
    @Transactional
    public void removeOwner(long id) {
        Owner dbOwner = entityManager.find(Owner.class, id);
        entityManager.remove(dbOwner);
    }

    /**
     * Gets all owners
     *
     * @return all Owners: {@code List<Owner>}
     */
    public List<Owner> getOwners() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Owner> query = cb.createQuery(Owner.class);
        return entityManager.createQuery(query.select(query.from(Owner.class))).getResultList();
    }

    /**
     * Gets a specific {@code Owner}
     *
     * @param id id of the owner to get
     * @return returns a {@code Owner}
     */
    public Owner getOwner(Long id) {
        return entityManager.find(Owner.class, id);
    }
}
