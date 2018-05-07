package com.kyrosoft.inventory.service;

import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.IdentifiableEntity;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.dto.BaseDTO;

/**
 * Base service interface
 *
 * @param <T> the entity
 * @param <S> the dto
 *
 * @author fahrur
 * @version 1.0
 */
public interface BaseService<T extends IdentifiableEntity,S extends BaseDTO> {

    /**
     * Get the entity
     *
     * @param id the id
     * @return the entity
     * @throws ServiceException the exception
     */
    public T get(Long id) throws ServiceException;

    /**
     * Create the entity
     *
     * @param entity the entity
     * @return the entity
     * @throws ServiceException the exception
     */
    public T create(T entity) throws ServiceException;

    /**
     * Update the entity
     *
     * @param entity the entity
     * @return the entity
     * @throws ServiceException the exception
     */
    public T update(T entity) throws ServiceException;

    /**
     * Delete the entity
     *
     * @param entity the entity
     * @throws ServiceException the exception
     */
    public void delete(T entity) throws ServiceException;

}
