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

    String createUser(CreateUserRequest createUserRequest);

    String createUserAddress(String userId, CreateUserAddressRequest createUserAddressRequest);

    void deleteUserAddress(String userId, String addressId);

    void deleteUserById(String userId);

    List<Address> getUserAddresses(String userId, Integer offset, Integer limit);

    User getUserById(String userId);

    List<User> getUsers(Integer offset, Integer limit);

    void patchUserAddress( String userId,  String addressId,  PatchUserAddressRequest patchUserAddressRequest);

    void patchUserById( String userId,  PatchUserByIdRequest patchUserByIdRequest);

    void updateUserAddress( String userId,  String addressId,  CreateUserAddressRequest createUserAddressRequest);

    void updateUserById( String userId,  UpdateUserByIdRequest updateUserByIdRequest);
}
