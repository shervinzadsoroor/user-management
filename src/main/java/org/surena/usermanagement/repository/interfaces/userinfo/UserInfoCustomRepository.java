package org.surena.usermanagement.repository.interfaces.userinfo;

import org.surena.usermanagement.domainmodel.userinfo.UserInfo;
import org.surena.usermanagement.dto.userinfo.UserInfoDeleteDto;
import org.surena.usermanagement.repository.interfaces.BaseCustomRepository;

public interface UserInfoCustomRepository extends BaseCustomRepository<UserInfo> {

    int deleteByUsername(UserInfoDeleteDto dto);
}
