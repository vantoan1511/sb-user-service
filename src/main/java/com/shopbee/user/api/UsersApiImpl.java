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

import java.net.URI;

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
        String userId = usersService.createUser(createUserRequest);
        URI location = uriInfo.getAbsolutePathBuilder().path(userId).build();
        return Response.created(location).entity(userId).build();
    }

    @Override
    public Response createUserAddress(String userId, @Valid @NotNull CreateUserAddressRequest createUserAddressRequest) {
        String addressId = usersService.createUserAddress(userId, createUserAddressRequest);
        URI location = uriInfo.getAbsolutePathBuilder().path(addressId).build();
        return Response.created(location).entity(addressId).build();
    }

    @Override
    public Response deleteUserAddress(String userId, String addressId) {
        usersService.deleteUserAddress(userId, addressId);
        return Response.noContent().build();
    }

    @Override
    public Response deleteUserById(String userId) {
        usersService.deleteUserById(userId);
        return Response.noContent().build();
    }

    @Override
    public Response getUserAddresses(String userId, @Min(0L) Integer offset, @Min(1L) Integer limit) {
        return Response.ok(usersService.getUserAddresses(userId, offset, limit)).build();
    }

    @Override
    public Response getUserById(String userId) {
        return Response.ok(usersService.getUserById(userId)).build();
    }

    @Override
    public Response getUsers(@Min(0L) Integer offset, @Min(1L) Integer limit) {
        return Response.ok(usersService.getUsers(offset, limit)).build();
    }

    @Override
    public Response patchUserAddress(String userId, String addressId, @Valid @NotNull PatchUserAddressRequest patchUserAddressRequest) {
        usersService.patchUserAddress(userId, addressId, patchUserAddressRequest);
        return Response.ok().build();
    }

    @Override
    public Response patchUserById(String userId, @Valid @NotNull PatchUserByIdRequest patchUserByIdRequest) {
        usersService.patchUserById(userId, patchUserByIdRequest);
        return Response.ok().build();
    }

    @Override
    public Response updateUserAddress(String userId, String addressId, @Valid @NotNull CreateUserAddressRequest createUserAddressRequest) {
        usersService.updateUserAddress(userId, addressId, createUserAddressRequest);
        return Response.ok().build();
    }

    @Override
    public Response updateUserById(String userId, @Valid @NotNull UpdateUserByIdRequest updateUserByIdRequest) {
        usersService.updateUserById(userId, updateUserByIdRequest);
        return Response.ok().build();
    }
}
