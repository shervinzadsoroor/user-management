package org.surena.usermanagement.service.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.surena.usermanagement.domainmodel.BaseEntity;
import org.surena.usermanagement.dto.BaseDto;
import org.surena.usermanagement.repository.interfaces.BaseCustomRepository;

public interface BaseService<
        E extends BaseEntity,
        D extends BaseDto,
        R extends JpaRepository<E, Long> & BaseCustomRepository<E>
        > {

    E getById(Long id);

    void save(E e);

    void edit(E entity, int version);

    void delete(E entity, int version);

    void deleteById(Long id, int version);
}
