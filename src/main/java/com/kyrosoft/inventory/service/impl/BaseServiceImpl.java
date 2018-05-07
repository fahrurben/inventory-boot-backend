package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.ActivableEntity;
import com.kyrosoft.inventory.model.AuditableEntity;
import com.kyrosoft.inventory.model.IdentifiableEntity;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.repository.BaseRepository;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

/**
 * Base service implementation, the class contain function for CRUD OPERATION
 *
 * @param <T> The entity
 * @param <S> The repository used for database operation
 *
 * @author fahrur
 * @version 1.0
 */
public class BaseServiceImpl<T extends IdentifiableEntity,
        S extends BaseRepository<T,Long>,
        U extends BaseDTO
        > {

    /**
     * Repository used for database operation
     */
    private S repository;

    /**
     * JPA entity validator
     */
    private Validator validator;

    /**
     * Entity class type
     */
    private Class<T> classType;

    /**
     * Constructor, pass repository class and class type
     *
     * @param repository repository for database operation
     * @param classType the class type of entity
     */
    protected BaseServiceImpl(S repository,Class<T> classType) {
        this.repository = repository;
        this.classType = classType;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Get messages from JPA validator and merge it into single string with comma separated
     *
     * @param violations the violations entity
     * @return the message
     */
    protected String getMessagesFromValidator(Set<ConstraintViolation<T>> violations) {
        String ret = "";

        int i=0;
        for (ConstraintViolation<T> violation : violations) {
            String message = violation.getPropertyPath().toString()+" "+violation.getMessage();
            ret += i==0 ? message : ", "+message;
            i++;
        }

        return ret;
    }

    /**
     * Set auditable entity for create operation
     *
     * @param entity the entity
     */
    protected void setAuditableInformationCreate(T entity) {
        ((AuditableEntity) entity).setCreatedBy(ServiceContext.getCurrentUser());
        ((AuditableEntity) entity).setCreatedDate(new Date());
        ((AuditableEntity) entity).setUpdatedBy(ServiceContext.getCurrentUser());
        ((AuditableEntity) entity).setUpdatedDate(new Date());
    }

    /**
     * Set auditable entity for update operation
     *
     * @param entity th entity
     */
    protected void setAuditableInformationUpdate(T entity) {
        ((AuditableEntity) entity).setUpdatedBy(ServiceContext.getCurrentUser());
        ((AuditableEntity) entity).setUpdatedDate(new Date());
    }

    /**
     * Get the entity
     *
     * @param id the id
     * @return the entity
     * @throws ServiceException the exception
     */
    public T get(Long id) throws ServiceException {

        T entity = null;

        try {
            entity = repository.findOne(id);
        }
        catch(PersistenceException e) {
            throw new ServiceException("Can't get "+classType.getCanonicalName(),e);
        }

        return entity;
    }

    /**
     * Create the entity
     *
     * @param entity the entity
     * @return the entity
     * @throws ServiceException the exception
     */
    public T create(T entity) throws ServiceException {

        // If instance is configuration entity then set the auditable information
        if(entity instanceof AuditableEntity) {
            setAuditableInformationCreate(entity);
        }

        // Checking if entity is valid or contains violation
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if(violations.size()>0) {
            String validationMessages = getMessagesFromValidator(violations);
            throw new ServiceException(validationMessages);
        }

        // Save the entity
        try {
            entity = repository.save(entity);
        }
        catch(PersistenceException e) {
            throw new ServiceException("Can't persist "+classType.getCanonicalName(),e);
        }

        return entity;
    }


    /**
     * Update the entity
     *
     * @param entity the entity
     * @return the entity
     * @throws ServiceException the exception
     */
    public T update(T entity) throws ServiceException {

        // If instance is configuration entity then set the auditable information
        if(entity instanceof AuditableEntity) {
            setAuditableInformationUpdate(entity);
        }

        // Checking if entity is valid or contains violation
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if(violations.size()>0) {
            String validationMessages = getMessagesFromValidator(violations);
            throw new ServiceException(validationMessages);
        }

        // Update the entity
        try {
            entity = repository.save(entity);
        }
        catch(PersistenceException e) {
            throw new ServiceException("Can't update "+classType.getCanonicalName(),e);
        }

        return entity;

    }

    /**
     * Delete the entity
     *
     * @param entity the entity
     * @throws ServiceException the exception
     */
    public void delete(T entity) throws ServiceException {

        // Delete configuration entity is not allowed
        if(entity instanceof ActivableEntity) {
            throw new ServiceException("Delete activable entity is not allowed");
        }

        try {
            repository.delete(entity);
        }
        catch(PersistenceException e) {
            throw new ServiceException("Can't delete "+classType.getCanonicalName(),e);
        }

    }

}
