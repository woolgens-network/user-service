package net.woolgens.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Getter
public class UserException extends Exception {

    private int status;

    public UserException(String message, int status) {
        super(message);
        this.status = status;
    }
}
