package org.surena.usermanagement.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.surena.usermanagement.domainmodel.BaseEntity;
import org.surena.usermanagement.repository.interfaces.BaseCustomRepository;

public abstract class BaseRepositoryImpl<E extends BaseEntity>
        implements BaseCustomRepository<E> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
