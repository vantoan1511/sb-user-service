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
import io.quarkus.security.Authenticated;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;

@ApplicationScoped
@Authenticated
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
    public Response createUser(@NotNull String tenantId, @Valid @NotNull CreateUserRequest createUserRequest) {
        String userId = usersService.createUser(tenantId, createUserRequest);
        URI location = uriInfo.getAbsolutePathBuilder().path(userId).build();
        return Response.created(location).entity(userId).build();
    }

    @Override
    public Response createUserAddress(@NotNull String tenantId, String userId, @Valid @NotNull CreateUserAddressRequest createUserAddressRequest) {
        String addressId = usersService.createUserAddress(tenantId, userId, createUserAddressRequest);
        URI location = uriInfo.getAbsolutePathBuilder().path(addressId).build();
        return Response.created(location).entity(addressId).build();
    }

    @Override
    public Response deleteUserAddress(@NotNull String tenantId, String userId, String addressId) {
        usersService.deleteUserAddress(tenantId, userId, addressId);
        return Response.noContent().build();
    }

    @Override
    public Response deleteUserById(@NotNull String tenantId, String userId) {
        usersService.deleteUserById(tenantId, userId);
        return Response.noContent().build();
    }

    @Override
    public Response getUserAddresses(@NotNull String tenantId, String userId, @Min(0L) Integer offset, @Min(1L) Integer limit) {
        return Response.ok(usersService.getUserAddresses(tenantId, userId, offset, limit)).build();
    }

    @Override
    public Response getUserById(@NotNull String tenantId, String userId) {
        return Response.ok(usersService.getUserById(tenantId, userId)).build();
    }

    @Override
    public Response getUsers(@NotNull String tenantId,
                             @QueryParam("offset") @DefaultValue("0") @Min(0L) Integer offset,
                             @QueryParam("limit") @DefaultValue("20") @Min(1L) Integer limit) {
        return Response.ok(usersService.getUsers(tenantId, offset, limit)).build();
    }

    @Override
    public Response patchUserAddress(@NotNull String tenantId,
                                     String userId,
                                     String addressId,
                                     @Valid @NotNull PatchUserAddressRequest patchUserAddressRequest) {
        usersService.patchUserAddress(tenantId, userId, addressId, patchUserAddressRequest);
        return Response.ok().build();
    }

    @Override
    public Response patchUserById(@NotNull String tenantId,
                                  String userId,
                                  @Valid @NotNull PatchUserByIdRequest patchUserByIdRequest) {
        usersService.patchUserById(tenantId, userId, patchUserByIdRequest);
        return Response.ok().build();
    }

    @Override
    public Response updateUserAddress(@NotNull String tenantId,
                                      String userId,
                                      String addressId,
                                      @Valid @NotNull CreateUserAddressRequest createUserAddressRequest) {
        usersService.updateUserAddress(tenantId, userId, addressId, createUserAddressRequest);
        return Response.ok().build();
    }

    @Override
    public Response updateUserById(@NotNull String tenantId,
                                   String userId,
                                   @Valid @NotNull UpdateUserByIdRequest updateUserByIdRequest) {
        usersService.updateUserById(tenantId, userId, updateUserByIdRequest);
        return Response.ok().build();
    }
}
