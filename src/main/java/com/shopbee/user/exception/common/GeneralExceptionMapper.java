package com.shopbee.user.exception.common;

import com.shopbee.user.exception.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        String message = "An error occurred while processing the request";
        String details = e.getMessage();
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(message, details)).build();
    }
}
