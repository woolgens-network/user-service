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
public class SeasonQuest {

    private Map<String, Map<String, SeasonQuestSelect>> selected;
    private Map<String, Long> finished;
}
