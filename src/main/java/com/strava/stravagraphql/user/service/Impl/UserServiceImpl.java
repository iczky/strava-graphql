package com.strava.stravagraphql.user.service.Impl;


import com.strava.stravagraphql.user.dto.CreateUserDto;
import com.strava.stravagraphql.user.dto.RegisterResponse;
import com.strava.stravagraphql.user.entity.User;
import com.strava.stravagraphql.user.mapper.CreateUserMapper;
import com.strava.stravagraphql.user.repository.UserRepository;
import com.strava.stravagraphql.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, CreateUserMapper createUserMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.createUserMapper = createUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public RegisterResponse register(CreateUserDto dto) {
        User user = createUserMapper.createUserDTOToUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return createUserMapper.userToRegisterResponse(user);
    }
}
