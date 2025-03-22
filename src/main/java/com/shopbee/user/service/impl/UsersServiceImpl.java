/*
 * UsersServiceImpl.java
 *
 * Copyright by  shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.service.impl;

import com.shopbee.user.exception.UserServiceException;
import com.shopbee.user.mapper.PhoneMapper;
import com.shopbee.user.mapper.UserMapper;
import com.shopbee.user.repository.PhoneRepository;
import com.shopbee.user.repository.UsersRepository;
import com.shopbee.user.service.UsersService;
import com.shopbee.user.v1.dto.Address;
import com.shopbee.user.v1.dto.CreateUserAddressRequest;
import com.shopbee.user.v1.dto.CreateUserRequest;
import com.shopbee.user.v1.dto.PatchUserAddressRequest;
import com.shopbee.user.v1.dto.PatchUserByIdRequest;
import com.shopbee.user.v1.dto.UpdateUserByIdRequest;
import com.shopbee.user.v1.dto.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PhoneRepository phoneRepository;
    private final UserMapper userMapper;
    private final PhoneMapper phoneMapper;

    /**
     * Instantiates a new Users service.
     *
     * @param usersRepository the users repository
     * @param phoneRepository the phone repository
     * @param userMapper      the user mapper
     * @param phoneMapper     the phone mapper
     */
    @Inject
    public UsersServiceImpl(UsersRepository usersRepository,
                            PhoneRepository phoneRepository,
                            UserMapper userMapper,
                            PhoneMapper phoneMapper) {
        this.usersRepository = usersRepository;
        this.phoneRepository = phoneRepository;
        this.userMapper = userMapper;
        this.phoneMapper = phoneMapper;
    }

    @Override
    public List<User> getUsers(Integer offset, Integer limit) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public User getUserById(String userId) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public String createUser(CreateUserRequest createUserRequest) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public void deleteUserById(String userId) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public List<Address> getUserAddresses(String userId, Integer offset, Integer limit) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public String createUserAddress(String userId, CreateUserAddressRequest createUserAddressRequest) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public void updateUserAddress(String userId, String addressId, CreateUserAddressRequest createUserAddressRequest) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public void patchUserAddress(String userId, String addressId, PatchUserAddressRequest patchUserAddressRequest) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }

    @Override
    public void deleteUserAddress(String userId, String addressId) {
        throw UserServiceException.notImplemented("Operation not implemented");
    }
}
