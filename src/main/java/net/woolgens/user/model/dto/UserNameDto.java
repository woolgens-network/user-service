package net.woolgens.user.model.dto;

import io.quarkus.mongodb.panache.common.ProjectionFor;
import lombok.Getter;
import lombok.Setter;
import net.woolgens.user.model.User;
import org.bson.codecs.pojo.annotations.BsonId;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Getter @Setter
@ProjectionFor(User.class)
public class UserNameDto {

    @BsonId
    private String uuid;
    private String name;
}
