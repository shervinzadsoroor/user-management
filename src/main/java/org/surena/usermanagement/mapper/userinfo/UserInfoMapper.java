package org.surena.usermanagement.mapper.userinfo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.surena.usermanagement.domainmodel.userinfo.UserInfo;
import org.surena.usermanagement.dto.userinfo.UserInfoDto;

@Mapper
public interface UserInfoMapper {

    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    UserInfoDto toDto(UserInfo entity);

    UserInfo toEntity(UserInfoDto dto);
}
