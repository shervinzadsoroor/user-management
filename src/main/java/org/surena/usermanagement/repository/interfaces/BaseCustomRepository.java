package org.surena.usermanagement.repository.interfaces;


import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityManager;
import org.surena.usermanagement.domainmodel.BaseEntity;

@Hidden
public interface BaseCustomRepository<E extends BaseEntity> {

    EntityManager getEntityManager();
}
