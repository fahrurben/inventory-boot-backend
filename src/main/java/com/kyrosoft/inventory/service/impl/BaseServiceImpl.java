package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.*;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.model.dto.SortType;
import com.kyrosoft.inventory.repository.BaseRepository;
import com.kyrosoft.inventory.service.PredicateBuilder;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.List;
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
public abstract class BaseServiceImpl<T extends IdentifiableEntity,
        S extends BaseRepository<T,Long>,
        U extends BaseDTO
        > {

    @PersistenceContext
    protected EntityManager em;

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
        ((AuditableEntity) entity).setCreatedBy("test");
        ((AuditableEntity) entity).setCreatedDate(new Date());
        ((AuditableEntity) entity).setUpdatedBy("test");
        ((AuditableEntity) entity).setUpdatedDate(new Date());
    }

    /**
     * Set auditable entity for update operation
     *
     * @param entity th entity
     */
    protected void setAuditableInformationUpdate(T entity) {
        ((AuditableEntity) entity).setUpdatedBy("test");
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

    /**
     * Get page request object from DTO, it's use for repository.find
     *
     * @param dto the dto object
     * @return the page request object
     */
    protected Pageable getPageRequestFromDTO(U dto) {
        Pageable pageable = null;
        if(dto.getSortBy()!=null && !dto.getSortBy().trim().equalsIgnoreCase("") && dto.getSortType()!=null ) {
            pageable = new PageRequest(dto.getPage() - 1,
                    dto.getSize(),
                    dto.getSortType() == SortType.ASC ? Sort.Direction.ASC : Sort.Direction.DESC,
                    dto.getSortBy()
            );
        }
        else {
            pageable = new PageRequest(dto.getPage() - 1, dto.getSize());
        }

        return pageable;
    }

    /**
     * Generate the search specification
     *
     * @param dto the data transfer object
     * @return the specifications
     */
    protected abstract List<Predicate> generateSearchSpecs(U dto, CriteriaBuilder criteriaBuilder, Root<T> root);

    /**
     * Search entities, filter by data tranfer object
     *
     * @param searchDTO the data transfer object
     * @return the search result
     * @throws ServiceException the exception
     */
    public SearchResult<T> search(U searchDTO) throws ServiceException {
        // Check if the page and size invalid
        if(searchDTO.getSize()==0) {
            searchDTO.setSize(10);
        }

        if(searchDTO.getPage()==0) {
            searchDTO.setPage(1);
        }

        SearchResult<T> searchResult = new SearchResult<T>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(classType);
        Root<T> root = q.from(classType);

        CriteriaQuery<T> criteriaQuery = q.select(root);
        CriteriaQuery<Long> criteriaCount = cb.createQuery(Long.class);
        criteriaCount.select(cb.count(criteriaCount.from(classType)));

        List<Predicate> predicates = generateSearchSpecs(searchDTO, cb, root);
        Predicate[] arrPredicates = predicates.toArray(new Predicate[predicates.size()]);
        criteriaQuery.where(cb.and(arrPredicates));

        TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult((searchDTO.getPage()-1) * searchDTO.getSize());
        typedQuery.setMaxResults(searchDTO.getSize());

        criteriaCount.where(cb.and(arrPredicates));
        try {
            Integer count = em.createQuery(criteriaCount).getSingleResult().intValue();
            Integer page = count / searchDTO.getSize();
            page += count % searchDTO.getSize() != 0 ? 1 : 0;
            List<T> data = typedQuery.getResultList();

            // Set search result data
            searchResult.setTotal(count);
            searchResult.setTotalPage(page);
            searchResult.setData(data);
        }
        catch(PersistenceException e) {
            throw new ServiceException("Error occur when search "+classType.getCanonicalName(),e);
        }

        return searchResult;
    }

}
