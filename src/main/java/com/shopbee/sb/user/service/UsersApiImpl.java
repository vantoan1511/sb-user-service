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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;

@ApplicationScoped
public class UsersApiImpl implements UsersApi {

    private final UsersService usersService;
    private final UriInfo uriInfo;

    @Inject
    public UsersApiImpl(UsersService usersService, UriInfo uriInfo) {
        this.usersService = usersService;
        this.uriInfo = uriInfo;
    }

    @Override
    public Response createUser(@Valid @NotNull CreateUserRequest createUserRequest) {
        String createdUserId = usersService.createUser(createUserRequest);
        URI location = uriInfo.getAbsolutePathBuilder().path(createdUserId).build();
        return Response.created(location).build();
    }

    @Override
    public Response deleteUserById(String userId) {
        usersService.deleteUserById(userId);
        return Response.noContent().build();
    }

    @Override
    public Response getUserById(String userId) {
        return Response.ok(usersService.getUserById(userId)).build();
    }

    @Override
    public Response getUsers(Integer offset, Integer limit) {
        return Response.ok(usersService.getUsers(offset, limit)).build();
    }

    @Override
    public Response patchUserById(String userId, @Valid @NotNull PatchUserByIdRequest patchUserByIdRequest) {
        usersService.patchUserById(userId, patchUserByIdRequest);
        return Response.ok().build();
    }

    @Override
    public Response updateUserById(String userId, @Valid @NotNull UpdateUserByIdRequest updateUserByIdRequest) {
        usersService.updateUserById(userId, updateUserByIdRequest);
        return Response.ok().build();
    }
}
