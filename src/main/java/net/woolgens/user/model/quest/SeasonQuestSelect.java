package net.woolgens.user.model.quest;

import lombok.Data;

import java.util.Map;


/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Data
public class SeasonQuestSelect {

    private int state;
    private Map<String, Long> objectives;
    private long timestamp;
}
