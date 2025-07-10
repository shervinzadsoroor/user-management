package org.surena.usermanagement.service.interfaces.userinfo;

import org.springframework.data.domain.Page;
import org.surena.usermanagement.domainmodel.userinfo.UserInfo;
import org.surena.usermanagement.dto.userinfo.*;
import org.surena.usermanagement.repository.interfaces.userinfo.UserInfoRepository;
import org.surena.usermanagement.service.interfaces.BaseService;
import org.surena.usermanagement.utils.CustomPageable;

public interface UserInfoService
        extends BaseService<UserInfo, UserInfoDto, UserInfoRepository> {

    Long save(UserInfoSaveDto dto);

    void deleteByUsername(UserInfoDeleteDto dto);

    int updateName(UserInfoUpdateDto dto);

    int updatePassword(UserInfoUpdatePasswordDto dto);

    UserInfoDto getDtoByUsername(String username);

    Page<UserInfoDto> paginate(CustomPageable<UserInfoSearchDto> pageable);
}
