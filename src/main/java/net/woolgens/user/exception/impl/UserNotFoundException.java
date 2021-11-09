package net.woolgens.user.exception.impl;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
public class UserNotFoundException extends UserException{


    public UserNotFoundException(String message) {
        super(message, 404);
    }
}
