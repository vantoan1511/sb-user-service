/*
 * UsersApiImpl.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import com.shopbee.sb.user.service.spec.v1.api.UsersApi;
import com.shopbee.sb.user.service.spec.v1.dto.CreateUserRequest;
import com.shopbee.sb.user.service.spec.v1.dto.PatchUserByIdRequest;
import com.shopbee.sb.user.service.spec.v1.dto.UpdateUserByIdRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;

public class UsersApiImpl implements UsersApi {

    @Override
    public Response createUser(@Valid @NotNull CreateUserRequest createUserRequest) {
        return null;
    }

    @Override
    public Response deleteUserById(String userId) {
        return null;
    }

    @Override
    public Response getUserById(String userId) {
        return null;
    }

    @Override
    public Response getUsers(Integer offset, Integer limit) {
        return null;
    }

    @Override
    public Response patchUserById(String userId, @Valid @NotNull PatchUserByIdRequest patchUserByIdRequest) {
        return null;
    }

    @Override
    public Response updateUserById(String userId, @Valid @NotNull UpdateUserByIdRequest updateUserByIdRequest) {
        return null;
    }
}
