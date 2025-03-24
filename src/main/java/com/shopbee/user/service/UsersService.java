/*
 * UsersService.java
 *
 * Copyright by shopbee-user-service, all rights reserved.
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

/**
 * Service interface for managing users and their addresses.
 */
public interface UsersService {

    /**
     * Retrieves a list of users with pagination.
     *
     * @param offset the starting point of the list
     * @param limit  the maximum number of users to return
     * @return a list of users
     */
    List<User> getUsers(Integer offset, Integer limit);

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user
     * @return the user with the specified ID
     */
    User getUserById(String userId);

    /**
     * Creates a new user.
     *
     * @param createUserRequest the request object containing user details
     * @return the ID of the created user
     */
    String createUser(CreateUserRequest createUserRequest);

    /**
     * Updates a user by their ID.
     *
     * @param userId                the ID of the user to update
     * @param updateUserByIdRequest the request object containing updated user details
     */
    void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest);

    /**
     * Partially updates a user by their ID.
     *
     * @param userId               the ID of the user to update
     * @param patchUserByIdRequest the request object containing partial user details
     */
    void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest);

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     */
    void deleteUserById(String userId);

    /**
     * Retrieves a list of addresses for a user with pagination.
     *
     * @param userId the ID of the user
     * @param offset the starting point of the list
     * @param limit  the maximum number of addresses to return
     * @return a list of addresses for the user
     */
    List<Address> getUserAddresses(String userId, Integer offset, Integer limit);

    /**
     * Creates a new address for a user.
     *
     * @param userId                   the ID of the user
     * @param createUserAddressRequest the request object containing address details
     * @return the ID of the created address
     */
    String createUserAddress(String userId, CreateUserAddressRequest createUserAddressRequest);

    /**
     * Updates an address for a user.
     *
     * @param userId                   the ID of the user
     * @param addressId                the ID of the address to update
     * @param createUserAddressRequest the request object containing updated address details
     */
    void updateUserAddress(String userId, String addressId, CreateUserAddressRequest createUserAddressRequest);

    /**
     * Partially updates an address for a user.
     *
     * @param userId                  the ID of the user
     * @param addressId               the ID of the address to update
     * @param patchUserAddressRequest the request object containing partial address details
     */
    void patchUserAddress(String userId, String addressId, PatchUserAddressRequest patchUserAddressRequest);

    /**
     * Deletes an address for a user.
     *
     * @param userId    the ID of the user
     * @param addressId the ID of the address to delete
     */
    void deleteUserAddress(String userId, String addressId);
}
