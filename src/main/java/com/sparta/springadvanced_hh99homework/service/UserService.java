package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.dto.SignupRequestDto;
import com.sparta.springadvanced_hh99homework.exception.ErrorCode;
import com.sparta.springadvanced_hh99homework.exception.HGPrivateException;
import com.sparta.springadvanced_hh99homework.model.User;
import com.sparta.springadvanced_hh99homework.model.UserRoleEnum;
import com.sparta.springadvanced_hh99homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_USER_ID);
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_OWNER_CODE);
            }
            role = UserRoleEnum.STORE_OWNER;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
    }
}