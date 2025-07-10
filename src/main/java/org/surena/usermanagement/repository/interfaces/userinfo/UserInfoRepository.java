package org.surena.usermanagement.repository.interfaces.userinfo;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import org.surena.usermanagement.domainmodel.userinfo.UserInfo;

@Repository
public interface UserInfoRepository
        extends JpaRepository<UserInfo, Long>, UserInfoCustomRepository {

    UserInfo findByUsername(String username);

    boolean existsByUsername(String username);

    @NonNull
    Page<UserInfo> findAll(@NonNull Pageable pageable);
}
