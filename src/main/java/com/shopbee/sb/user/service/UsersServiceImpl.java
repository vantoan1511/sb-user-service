/*
 * UsersServiceImpl.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import com.shopbee.sb.user.service.spec.v1.dto.CreateUserRequest;
import com.shopbee.sb.user.service.spec.v1.dto.PatchUserByIdRequest;
import com.shopbee.sb.user.service.spec.v1.dto.UpdateUserByIdRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final GenderMapper genderMapper;

    @Inject
    public UsersServiceImpl(UsersRepository usersRepository,
                            UserMapper userMapper,
                            GenderMapper genderMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
        this.genderMapper = genderMapper;
    }

    @Override
    @Transactional
    public String createUser(CreateUserRequest createUserRequest) throws UserServiceException {
        //TODO: Validate request (username exists, email exists, valid gender, ...)
        User user = userMapper.toUser(createUserRequest);
        usersRepository.persist(user);
        return user.getId();
    }

    @Override
    @Transactional
    public void deleteUserById(String userId) throws UserServiceException {
        usersRepository.deleteById(userId);
    }

    @Override
    public User getUserById(String userId) throws UserServiceException {
        return usersRepository.findByIdOptional(userId).orElseThrow(() -> createUserNotFoundException(userId));
    }

    @Override
    public List<User> getUsers(Integer offset, Integer limit) {
        return usersRepository.findAll().page(offset, limit).list();
    }

    @Override
    @Transactional
    public void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest) throws UserServiceException {
        User existingUser = getUserById(userId);
        //TODO: Validate new email is linked to another user or not
        userMapper.patchUser(patchUserByIdRequest, existingUser);
    }

    @Override
    @Transactional
    public void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest) throws UserServiceException {
        User existingUser = getUserById(userId);
        //TODO: Validate new email is linked to another user or not
        userMapper.updateUser(updateUserByIdRequest, existingUser);
    }

    private UserServiceException createUserNotFoundException(String userId) {
        String message = String.format("Cannot find user with id %s.", userId);
        return UserServiceException.create(UserServiceException.Type.NOT_FOUND, message);
    }

}
