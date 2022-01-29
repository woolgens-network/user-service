package net.woolgens.user.resource.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@AllArgsConstructor
@Data
public class UserCountResponse {

    private int maxPages;
    private long count;
}
