package net.woolgens.user.exception;

import net.woolgens.user.exception.impl.UserException;
import net.woolgens.user.exception.response.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Provider
public class UserExceptionMapper implements ExceptionMapper<UserException> {

    @Override
    public Response toResponse(UserException exception) {
        return Response.status(exception.getStatus()).entity(new ErrorResponse(exception.getMessage())).build();
    }

}
