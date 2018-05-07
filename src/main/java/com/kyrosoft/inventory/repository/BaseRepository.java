package com.kyrosoft.inventory.repository;

import com.kyrosoft.inventory.model.IdentifiableEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Base repository interface
 * @param <T> Entity
 * @param <ID> Id of entity
 *
 * @author fahrur
 * @version 1.0
 */
@NoRepositoryBean
public interface BaseRepository <T extends IdentifiableEntity, ID extends Serializable>
        extends Repository<T, ID>, JpaSpecificationExecutor<T> {


    /**
     * Save entity
     * @param entity the entity
     * @return the entity
     */
    T save(T entity);

    /**
     * Find one
     * @param id
     * @return
     */
    T findOne(ID id);

    /**
     * Find All
     *
     * @return the list data
     */
    Iterable<T> findAll();

    /**
     * Count
     *
     * @return the count
     */
    Long count();

    /**
     * Delete
     *
     * @param entity the entity
     */
    void delete(T entity);


    /**
     * Is entity exist
     *
     * @param primaryKey the primary key
     * @return is entity exist
     */
    boolean exists(ID primaryKey);

}
