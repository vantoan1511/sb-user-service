/*
 * UsersServiceExceptionMapper.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UsersServiceExceptionMapper implements ExceptionMapper<UserServiceException> {

    @Override
    public Response toResponse(UserServiceException userServiceException) {
        Response.Status status = getResponseStatus(userServiceException);
        return Response.status(status)
            .entity(ErrorMessage.create(userServiceException.getMessage()))
            .type(MediaType.APPLICATION_JSON)
            .build();
    }

    private Response.Status getResponseStatus(UserServiceException userServiceException) {
        UserServiceException.Type type = userServiceException.getType();
        return switch (type) {
            case NOT_FOUND -> Response.Status.NOT_FOUND;
            case INTERNAL_ERROR -> Response.Status.INTERNAL_SERVER_ERROR;
            case null -> Response.Status.SERVICE_UNAVAILABLE;
        };
    }

}
