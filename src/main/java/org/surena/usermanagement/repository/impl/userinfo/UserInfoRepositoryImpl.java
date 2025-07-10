package org.surena.usermanagement.repository.impl.userinfo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.surena.usermanagement.domainmodel.userinfo.UserInfo;
import org.surena.usermanagement.dto.userinfo.UserInfoDeleteDto;
import org.surena.usermanagement.repository.impl.BaseRepositoryImpl;
import org.surena.usermanagement.repository.interfaces.userinfo.UserInfoCustomRepository;

@Repository
public class UserInfoRepositoryImpl
        extends BaseRepositoryImpl<UserInfo>
        implements UserInfoCustomRepository {

    @Transactional
    @Override
    public int deleteByUsername(UserInfoDeleteDto dto) {
        return getEntityManager().createQuery(
                        "delete from UserInfo u " +
                                " where u.username = :username and u.version = :version")
                .setParameter("username", dto.getUsername())
                .setParameter("version", dto.getVersion())
                .executeUpdate();
    }
}
