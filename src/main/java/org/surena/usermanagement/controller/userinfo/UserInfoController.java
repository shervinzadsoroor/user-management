package org.surena.usermanagement.controller.userinfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.surena.usermanagement.dto.BaseLightDto;
import org.surena.usermanagement.dto.userinfo.*;
import org.surena.usermanagement.service.interfaces.userinfo.UserInfoService;
import org.surena.usermanagement.utils.CustomPageable;
import org.surena.usermanagement.validation.Delete;
import org.surena.usermanagement.validation.Edit;

@Tag(name = "User Info Controller")
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserInfoController {

    private final UserInfoService service;

    @Operation(summary = "add new user")
    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody @Validated UserInfoSaveDto dto) {
        service.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "delete by id")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteById(
            @RequestBody @Validated(Delete.class) BaseLightDto dto) {
        service.deleteById(dto.getId(), dto.getVersion());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "delete by username")
    @DeleteMapping(value = "/delete-by-username")
    public ResponseEntity<?> deleteByUsername(
            @RequestBody @Validated(Delete.class) UserInfoDeleteDto dto) {
        service.deleteByUsername(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "partial update (firstname & lastname)")
    @PatchMapping(value = "/update")
    public ResponseEntity<Integer> updateName(
            @RequestBody @Validated(Edit.class) UserInfoUpdateDto dto) {
        int version = service.updateName(dto);
        return new ResponseEntity<>(version, HttpStatus.OK);
    }

    @Operation(summary = "change password")
    @PatchMapping(value = "/update/password")
    public ResponseEntity<Integer> updatePassword(
            @RequestBody @Validated UserInfoUpdatePasswordDto dto) {
        int version = service.updatePassword(dto);
        return new ResponseEntity<>(version, HttpStatus.OK);
    }

    @Operation(summary = "get user detail by username")
    @GetMapping(value = "/get/{username}")
    public ResponseEntity<UserInfoDto> get(@PathVariable String username) {
        UserInfoDto result = service.getDtoByUsername(username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "get pagination")
    @PostMapping(value = "/paginate")
    public ResponseEntity<Page<UserInfoDto>> paginate(
            @RequestBody CustomPageable<UserInfoSearchDto> pageable) {
        Page<UserInfoDto> page = service.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

}
