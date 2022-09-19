package com.btem.dev.app.service;

import com.btem.dev.app.dto.UserInsertDto;
import com.btem.dev.app.dto.UserResponseDto;
import com.btem.dev.app.dto.UserUpdateDto;

public interface UserService {
    UserResponseDto getUser(String id) throws Exception;
    void insertUser(UserInsertDto user) throws Exception;
    void updateUser(UserUpdateDto user) throws Exception;
    void deleteUser(String id) throws Exception;
}
