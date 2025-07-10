package org.surena.usermanagement.service.impl.userinfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.surena.usermanagement.domainmodel.userinfo.UserInfo;
import org.surena.usermanagement.dto.userinfo.*;
import org.surena.usermanagement.exception.InvalidPasswordException;
import org.surena.usermanagement.mapper.userinfo.UserInfoMapper;
import org.surena.usermanagement.repository.interfaces.userinfo.UserInfoRepository;
import org.surena.usermanagement.service.impl.BaseServiceImpl;
import org.surena.usermanagement.service.interfaces.userinfo.UserInfoService;
import org.surena.usermanagement.utils.CustomPageable;
import org.surena.usermanagement.utils.PasswordService;

@Service
public class UserInfoServiceImpl
        extends BaseServiceImpl<UserInfo, UserInfoDto, UserInfoRepository>
        implements UserInfoService {

    private final UserInfoMapper mapper = UserInfoMapper.INSTANCE;

    public UserInfoServiceImpl(UserInfoRepository repository) {
        super(repository);
    }

    @Transactional
    @Override
    public void save(UserInfoSaveDto dto) {
        validateUsernameUniqueness(dto.getUsername());
        UserInfo entity = new UserInfo();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        save(entity);
    }

    private void validateUsernameUniqueness(String username) {
        if (repository.existsByUsername(username))
            throw new RuntimeException("Username " + username + " already exists");
    }

    @Transactional
    @Override
    public void deleteByUsername(UserInfoDeleteDto dto) {
        if (repository.deleteByUsername(dto) == 0) {
            throw new RuntimeException("id or version not matched! no row deleted");
        }
    }

    /**
     * note that edit method is transactional and there is no need to use @Transaction on updateName method
     * otherwise the entity.getVersion() will not return the incremented version
     *
     * @param dto UserInfoUpdateDto
     * @return int version
     */
    @Override
    public int updateName(UserInfoUpdateDto dto) {
        UserInfo entity = getById(dto.getId());
        if (entity == null) {
            throw new RuntimeException("id not found!");
        }
        if (StringUtils.isNotEmpty(dto.getFirstName())) {
            entity.setFirstName(dto.getFirstName());
        }
        if (StringUtils.isNotEmpty(dto.getLastName())) {
            entity.setLastName(dto.getLastName());
        }
        edit(entity, dto.getVersion());
        return entity.getVersion();
    }

    /**
     * note that edit method is transactional and there is no need to use @Transaction on updatePassword method
     * otherwise the entity.getVersion() will not return the incremented version
     *
     * @param dto UserInfoUpdatePasswordDto
     * @return int version
     */
    @Override
    public int updatePassword(UserInfoUpdatePasswordDto dto) {
        validatePassword(dto);
        UserInfo entity = repository.findByUsername(dto.getUsername());
        boolean isOldPassValid = PasswordService.verifyPassword(dto.getOldPassword(), entity.getPassword());
        if (!isOldPassValid) {
            throw new InvalidPasswordException("old password not matched!");
        }
        entity.setPassword(dto.getNewPassword());
        edit(entity, dto.getVersion());
        return entity.getVersion();
    }

    private void validatePassword(UserInfoUpdatePasswordDto dto) {
        if (StringUtils.isEmpty(dto.getOldPassword()) ||
                StringUtils.isEmpty(dto.getNewPassword()) ||
                !dto.getNewPassword().equals(dto.getNewPasswordConfirm())) {
            throw new InvalidPasswordException();
        }
    }

    @Override
    public UserInfoDto getDtoByUsername(String username) {
        UserInfo user = repository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("user not found!");
        }
        return mapper.toDto(user);
    }

    @Override
    public Page<UserInfoDto> paginate(CustomPageable<UserInfoSearchDto> cp) {
        Pageable pageable = PageRequest.of(
                cp.getPage(),
                cp.getSize(),
                cp.getDirection(),
                cp.getSortBy()
        );
        Page<UserInfo> pagedResult = repository.findAll(pageable);
        return pagedResult.map(mapper::toDto);
    }
}
