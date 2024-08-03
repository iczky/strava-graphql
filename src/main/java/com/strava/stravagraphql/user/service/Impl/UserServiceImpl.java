package com.strava.stravagraphql.user.service.Impl;


import com.strava.stravagraphql.user.dto.CreateUserDto;
import com.strava.stravagraphql.user.dto.RegisterResponse;
import com.strava.stravagraphql.user.entity.User;
import com.strava.stravagraphql.user.mapper.CreateUserMapper;
import com.strava.stravagraphql.user.repository.UserRepository;
import com.strava.stravagraphql.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;

    public UserServiceImpl(UserRepository userRepository, CreateUserMapper createUserMapper) {
        this.userRepository = userRepository;
        this.createUserMapper = createUserMapper;
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public RegisterResponse register(CreateUserDto dto) {
        User user = createUserMapper.createUserDTOToUser(dto);
        userRepository.save(user);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(user.getId());
        registerResponse.setEmail(user.getEmail());
        registerResponse.setUsername(user.getUsername());
        return registerResponse;
    }
}
