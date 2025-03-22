/*
 * UsersService.java
 *
 * Copyright by  shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.service;

import com.shopbee.user.v1.dto.Address;
import com.shopbee.user.v1.dto.CreateUserAddressRequest;
import com.shopbee.user.v1.dto.CreateUserRequest;
import com.shopbee.user.v1.dto.PatchUserAddressRequest;
import com.shopbee.user.v1.dto.PatchUserByIdRequest;
import com.shopbee.user.v1.dto.UpdateUserByIdRequest;
import com.shopbee.user.v1.dto.User;

import java.util.List;

public interface UsersService {

    List<User> getUsers(Integer offset, Integer limit);

    User getUserById(String userId);

    String createUser(CreateUserRequest createUserRequest);

    void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest);

    void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest);

    void deleteUserById(String userId);

    List<Address> getUserAddresses(String userId, Integer offset, Integer limit);

    String createUserAddress(String userId, CreateUserAddressRequest createUserAddressRequest);

    void updateUserAddress(String userId, String addressId, CreateUserAddressRequest createUserAddressRequest);

    void patchUserAddress(String userId, String addressId, PatchUserAddressRequest patchUserAddressRequest);

    void deleteUserAddress(String userId, String addressId);
}
