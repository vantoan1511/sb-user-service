/*
 * UsersServiceImpl.java
 *
 * Copyright by  shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.service.impl;

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
    public String createUser(CreateUserRequest createUserRequest) {
        return "";
    }

    @Override
    public String createUserAddress(String userId, CreateUserAddressRequest createUserAddressRequest) {
        return "";
    }

    @Override
    public void deleteUserAddress(String userId, String addressId) {

    }

    @Override
    public void deleteUserById(String userId) {

    }

    @Override
    public List<Address> getUserAddresses(String userId, Integer offset, Integer limit) {
        return List.of();
    }

    @Override
    public User getUserById(String userId) {
        return null;
    }

    @Override
    public List<User> getUsers(Integer offset, Integer limit) {
        return List.of();
    }

    @Override
    public void patchUserAddress(String userId, String addressId, PatchUserAddressRequest patchUserAddressRequest) {

    }

    @Override
    public void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest) {

    }

    @Override
    public void updateUserAddress(String userId, String addressId, CreateUserAddressRequest createUserAddressRequest) {

    }

    @Override
    public void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest) {

    }
}
