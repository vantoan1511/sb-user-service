/*
 * UsersService.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import com.shopbee.sb.user.service.exception.UserServiceException;
import com.shopbee.sb.user.service.model.User;
import com.shopbee.sb.user.service.spec.v1.dto.CreateUser201Response;
import com.shopbee.sb.user.service.spec.v1.dto.CreateUserRequest;
import com.shopbee.sb.user.service.spec.v1.dto.GetUsers200Response;
import com.shopbee.sb.user.service.spec.v1.dto.PatchUserByIdRequest;
import com.shopbee.sb.user.service.spec.v1.dto.UpdateUserByIdRequest;
import java.util.List;

public interface UsersService {

    /**
     * Create new user.
     *
     * @param createUserRequest the create user request
     * @return the created user id
     * @throws UserServiceException the user service exception
     */
    CreateUser201Response createUser(CreateUserRequest createUserRequest);

    /**
     * Delete user by id.
     *
     * @param userId the user id
     * @throws UserServiceException the user service exception
     */
    void deleteUserById(String userId);

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     * @throws UserServiceException the user service exception
     */
    User getUserById(String userId);

    /**
     * Gets users.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the users
     */
    List<User> getUsers(Integer offset, Integer limit);

    /**
     * Gets paginated users.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the paginated users
     */
    GetUsers200Response getPaginatedUsers(Integer offset, Integer limit);

    /**
     * Patch user by id.
     *
     * @param userId               the user id
     * @param patchUserByIdRequest the patch user by id request
     * @throws UserServiceException the user service exception
     */
    void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest);

    /**
     * Update user by id.
     *
     * @param userId                the user id
     * @param updateUserByIdRequest the update user by id request
     * @throws UserServiceException the user service exception
     */
    void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest);
}
