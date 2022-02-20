package net.woolgens.user.model;

import lombok.Data;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Data
public class Badge {

    private String title;
    private String color;
    private String description;
    private long received;
}
