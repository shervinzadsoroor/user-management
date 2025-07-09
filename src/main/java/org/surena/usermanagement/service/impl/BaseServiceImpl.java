package org.surena.usermanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.surena.usermanagement.domainmodel.BaseEntity;
import org.surena.usermanagement.dto.BaseDto;
import org.surena.usermanagement.repository.interfaces.BaseCustomRepository;
import org.surena.usermanagement.service.interfaces.BaseService;

import java.util.Optional;

@Slf4j
public abstract class BaseServiceImpl<
        E extends BaseEntity,
        D extends BaseDto,
        R extends JpaRepository<E, Long> & BaseCustomRepository<E>
        >
        implements BaseService<E, D, R> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E getById(Long id) {
        Optional<E> optEntity = Optional.empty();
        try {
            optEntity = repository.findById(id);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        return optEntity.orElse(null);
    }

    @Override
    @Transactional
    public void save(E entity) {
        repository.save(entity);
    }

    @Override
    public void edit(E entity, int version) {
        if (entity == null) {
            throw new RuntimeException("entity not found!");
        }
        if (entity.getVersion() != version) {
            throw new OptimisticLockingFailureException("خطای همزمانی!");
        }
        repository.save(entity);
    }

    @Override
    public void delete(E entity, int version) {
        if (entity == null) {
            throw new RuntimeException("entity not found!");
        }
        if (entity.getVersion() != version) {
            throw new OptimisticLockingFailureException("خطای همزمانی!");
        }
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id, int version) {
        delete(getById(id), version);
    }
}
