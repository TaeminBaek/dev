package com.btem.dev.security.service;

import com.btem.dev.app.domain.User;
import com.btem.dev.app.repository.UserRepository;
import com.btem.dev.security.authentication.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final UserRepository userRepository;

    public UserInfo getUserInfo(String id, String pwd) throws Exception {
        Optional<User> findResult = userRepository.findById(id);
        if(!findResult.isPresent() || !findResult.get().getPwd().equals(pwd)) throw new Exception("아이디 또는 비밀번호가 잘못되었습니다.");
        UserInfo userInfo = new UserInfo(findResult.get());
        return userInfo;
    }
}
