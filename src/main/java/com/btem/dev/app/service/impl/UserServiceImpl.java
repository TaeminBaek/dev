package com.btem.dev.app.service.impl;

import com.btem.dev.app.dto.UserResponseDto;
import com.btem.dev.app.dto.UserInsertDto;
import com.btem.dev.app.dto.UserUpdateDto;
import com.btem.dev.app.service.UserService;
import com.btem.dev.app.domain.User;
import com.btem.dev.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUser(String id) throws Exception{
        return new UserResponseDto(userRepository.findById(id).orElseThrow(() -> new Exception("조회된 정보가 없습니다.")));
    }

    @Override
    @Transactional
    public void insertUser(UserInsertDto user) throws Exception {
        if(userRepository.findById(user.getId()).isPresent())    throw new Exception("중복된 아이디 입니다.");
        userRepository.save(new User(user.getId(), user.getName(), user.getPwd(), user.getPhoneNumb(), user.getEmailAddr()));
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateDto user) throws Exception {
        Optional<User> findResult = userRepository.findById(user.getId());
        if(!findResult.isPresent()) throw new Exception("조회된 정보가 없습니다.");

        User userInfo = findResult.get();
        userInfo.setName(user.getName());
        userInfo.setPhoneNumb(user.getPhoneNumb());
        userInfo.setEmailAddr(user.getEmailAddr());
    }

    @Override
    @Transactional
    public void deleteUser(String id) throws Exception {
        if(!userRepository.findById(id).isPresent()) throw new Exception("조회된 정보가 없습니다.");
        userRepository.deleteById(id);
    }
}
