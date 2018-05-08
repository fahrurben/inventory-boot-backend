package com.kyrosoft.inventory.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface PredicateBuilder<T> {
    Predicate build(CriteriaBuilder criteriaBuilder, Root<T> root);
}