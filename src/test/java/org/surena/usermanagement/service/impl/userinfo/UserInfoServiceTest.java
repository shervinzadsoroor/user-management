package org.surena.usermanagement.service.impl.userinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.surena.usermanagement.domainmodel.userinfo.UserInfo;
import org.surena.usermanagement.dto.userinfo.*;
import org.surena.usermanagement.exception.DuplicateUsernameException;
import org.surena.usermanagement.repository.interfaces.userinfo.UserInfoRepository;
import org.surena.usermanagement.utils.CustomPageable;
import org.surena.usermanagement.utils.PasswordService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class UserInfoServiceTest {

    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private UserInfoServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void createUser_ShouldSuccess_WhenUsernameIsUnique() {
        UserInfoSaveDto dto = new UserInfoSaveDto();
        dto.setUsername("uniqueUser");
        dto.setPassword("Pass@123");

        Long id = userService.save(dto);
        assertNotNull(id);

        UserInfo result = userService.getById(id);
        assertNotNull(result);
        assertEquals("uniqueUser", result.getUsername());
    }

    @Test
    void createUser_ShouldThrow_WhenUsernameExists() {
        UserInfoSaveDto dto = new UserInfoSaveDto();
        dto.setUsername("uniqueUser");
        dto.setPassword("Pass@123");
        userService.save(dto);

        assertThrows(DuplicateUsernameException.class, () -> {
            userService.save(dto);
        });
    }

    @Test
    void deleteById() {
        UserInfoSaveDto dto = new UserInfoSaveDto();
        dto.setUsername("uniqueUser");
        dto.setPassword("Pass@123");
        Long id = userService.save(dto);
        assertNotNull(id);
        UserInfo result = userService.getById(id);
        assertNotNull(result);

        userService.deleteById(id, result.getVersion());
        UserInfo deleted = userService.getById(result.getId());

        assertNull(deleted);
    }

    @Test
    void deleteByUsername() {
        UserInfoSaveDto dto = new UserInfoSaveDto();
        dto.setUsername("uniqueUser");
        dto.setPassword("Pass@123");
        Long id = userService.save(dto);
        UserInfo result = userService.getById(id);
        assertNotNull(result);

        UserInfoDeleteDto deleteDto = new UserInfoDeleteDto();
        deleteDto.setUsername("uniqueUser");
        deleteDto.setVersion(result.getVersion());
        userService.deleteByUsername(deleteDto);

        UserInfo deleted = userService.getById(result.getId());
        assertNull(deleted);
    }

    @Test
    void updateName() {
        UserInfoSaveDto dto = new UserInfoSaveDto();
        dto.setUsername("uniqueUser");
        dto.setPassword("Pass@123");
        dto.setFirstName("shervin");
        dto.setLastName("zadsoroor");

        Long id = userService.save(dto);
        assertNotNull(id);

        UserInfo result = userService.getById(id);
        assertNotNull(result);

        UserInfoUpdateDto updateDto = new UserInfoUpdateDto();
        updateDto.setId(id);
        updateDto.setVersion(result.getVersion());
        updateDto.setFirstName("ali");
        updateDto.setLastName("azad");
        userService.updateName(updateDto);

        UserInfo updated = userService.getById(id);

        assertEquals("ali", updated.getFirstName());
        assertEquals("azad", updated.getLastName());
    }

    @Test
    void updatePassword() {
        UserInfoSaveDto dto = new UserInfoSaveDto();
        dto.setUsername("uniqueUser");
        dto.setPassword("Pass@123");
        dto.setFirstName("shervin");
        dto.setLastName("zadsoroor");

        Long id = userService.save(dto);
        assertNotNull(id);

        UserInfo result = userService.getById(id);
        assertNotNull(result);

        UserInfoUpdatePasswordDto passwordDto = new UserInfoUpdatePasswordDto();
        passwordDto.setUsername("uniqueUser");
        passwordDto.setVersion(result.getVersion());
        passwordDto.setOldPassword("Pass@123");
        passwordDto.setNewPassword("newPass@123");
        passwordDto.setNewPasswordConfirm("newPass@123");
        userService.updatePassword(passwordDto);

        UserInfo updated = userService.getById(id);
        boolean matches = PasswordService.verifyPassword("newPass@123", updated.getPassword());
        assertTrue(matches);
    }

    @Test
    void getDtoByUsername() {
        UserInfoSaveDto dto = new UserInfoSaveDto();
        dto.setUsername("uniqueUser");
        dto.setPassword("Pass@123");
        userService.save(dto);

        UserInfoDto result = userService.getDtoByUsername("uniqueUser");
        assertNotNull(result);
    }

    @Test
    void paginate() {
        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            UserInfo entity = new UserInfo();
            entity.setUsername("uniqueUser" + i);
            entity.setPassword("Pass@123");
            list.add(entity);
        }
        userRepository.saveAll(list);

        CustomPageable<UserInfoSearchDto> pageable = new CustomPageable<>();
        pageable.setSearchDto(new UserInfoSearchDto());
        pageable.setPage(0);
        pageable.setSize(10);
        pageable.setSortBy("username");
        Page<UserInfoDto> page = userService.paginate(pageable);

        assertNotNull(page);
        assertNotNull(page.getContent());
        assertEquals(10, page.getContent().size());
        assertEquals(20, page.getTotalElements());
        assertEquals(2, page.getTotalPages());
        assertEquals("uniqueUser9", page.getContent().get(0).getUsername());
    }
}