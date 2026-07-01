package com.project.banking_api.src.adapters.out.implementations;

import com.project.banking_api.src.entities.User;
import com.project.banking_api.src.exceptions.NotFoundException;
import com.project.banking_api.src.infra.models.UserModel;
import com.project.banking_api.src.infra.repositories.UserRepositoryDB;
import com.project.banking_api.src.mappers.UserMapper;
import com.project.banking_api.src.services.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private UserRepositoryDB userRepository;
    private UserMapper userMapper;

    public UserRepositoryImpl(UserRepositoryDB userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User create(User user){

        UserModel userModel = userMapper.toModel(user);

        UserModel userCreated = userRepository.save(userModel);

        return userMapper.toEntity(userCreated);
    }

    @Override
    public User findByID(UUID id){
        UserModel findUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));

        return userMapper.toEntity(findUser);
    }

    @Override
    public User findByEmail(String email) {

        UserModel findUser = userRepository.findByEmail(email);

        if(findUser == null){
            throw new NotFoundException("User Not Found");
        }

        return userMapper.toEntity(findUser);

    }
}
