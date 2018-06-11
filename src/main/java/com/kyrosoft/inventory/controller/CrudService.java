package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.IdentifiableEntity;
import com.kyrosoft.inventory.service.BaseService;

public interface CrudService<T extends BaseService, U extends IdentifiableEntity> {
    void doCrud(T service, U entity) throws ServiceException;
}
