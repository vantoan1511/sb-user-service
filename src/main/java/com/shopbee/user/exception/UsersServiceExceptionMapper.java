/*
 * UsersServiceExceptionMapper.java
 *
 * Copyright by  shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Optional;

@Provider
public class UsersServiceExceptionMapper implements ExceptionMapper<UserServiceException> {

    @Override
    public Response toResponse(UserServiceException userServiceException) {
        Response.Status status = getResponseStatus(userServiceException);
        return Response.status(status)
            .entity(new ErrorMessage(userServiceException.getMessage()))
            .type(MediaType.APPLICATION_JSON)
            .build();
    }

    /**
     * Gets response status.
     *
     * @param userServiceException the user service exception
     * @return the response status
     */
    private Response.Status getResponseStatus(UserServiceException userServiceException) {
        return Optional.ofNullable(userServiceException)
            .map(UserServiceException::getResponse)
            .map(Response::getStatusInfo)
            .map(Response.StatusType::toEnum)
            .orElse(Response.Status.INTERNAL_SERVER_ERROR);
    }

}
