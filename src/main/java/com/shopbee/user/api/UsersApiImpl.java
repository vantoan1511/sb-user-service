/*
 * UsersApiImpl.java
 *
 * Copyright by shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.api;

import com.shopbee.user.service.UsersService;
import com.shopbee.user.v1.api.UsersApi;
import com.shopbee.user.v1.dto.CreateUserAddressRequest;
import com.shopbee.user.v1.dto.CreateUserRequest;
import com.shopbee.user.v1.dto.PatchUserAddressRequest;
import com.shopbee.user.v1.dto.PatchUserByIdRequest;
import com.shopbee.user.v1.dto.UpdateUserByIdRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@ApplicationScoped
public class UsersApiImpl implements UsersApi {

    private final UsersService usersService;
    private final UriInfo uriInfo;

    /**
     * Instantiates a new Users api.
     *
     * @param usersService the users service
     * @param uriInfo      the uri info
     */
    @Inject
    public UsersApiImpl(UsersService usersService, UriInfo uriInfo) {
        this.usersService = usersService;
        this.uriInfo = uriInfo;
    }

    @Override
    public Response createUser(@Valid @NotNull CreateUserRequest createUserRequest) {
        return null;
    }

    @Override
    public Response createUserAddress(String userId, @Valid @NotNull CreateUserAddressRequest createUserAddressRequest) {
        return null;
    }

    @Override
    public Response deleteUserAddress(String userId, String addressId) {
        return null;
    }

    @Override
    public Response deleteUserById(String userId) {
        return null;
    }

    @Override
    public Response getUserAddresses(String userId, @Min(0L) Integer offset, @Min(1L) Integer limit) {
        return null;
    }

    @Override
    public Response getUserById(String userId) {
        return null;
    }

    @Override
    public Response getUsers(@Min(0L) Integer offset, @Min(1L) Integer limit) {
        return null;
    }

    @Override
    public Response patchUserAddress(String userId, String addressId, @Valid @NotNull PatchUserAddressRequest patchUserAddressRequest) {
        return null;
    }

    @Override
    public Response patchUserById(String userId, @Valid @NotNull PatchUserByIdRequest patchUserByIdRequest) {
        return null;
    }

    @Override
    public Response updateUserAddress(String userId, String addressId, @Valid @NotNull CreateUserAddressRequest createUserAddressRequest) {
        return null;
    }

    @Override
    public Response updateUserById(String userId, @Valid @NotNull UpdateUserByIdRequest updateUserByIdRequest) {
        return null;
    }
}
