package net.woolgens.user.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@MongoEntity(collection = "users")
@Data
@NoArgsConstructor
public class User {

    @BsonId
    private String uuid;

    public User(String uuid) {
        this.uuid = uuid;
    }
}
