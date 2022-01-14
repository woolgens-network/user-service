package net.woolgens.user.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.Map;

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
    private String name;

    //----------------------------------------
    private String land;
    //----------------------------------------

    //----------------------------------------
    // Global
    private long joined;
    private Map<String, Long> stats;
    private Map<String, Object> settings;
    private Map<String, Object> extensions;
    //----------------------------------------

    private Map<String, Season> seasons;


    public User(String uuid) {
        this.uuid = uuid;
    }
}
