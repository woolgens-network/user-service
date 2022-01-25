package net.woolgens.user.model;

import lombok.Data;
import net.woolgens.user.model.quest.SeasonQuest;
import net.woolgens.user.model.skills.Skills;

import java.util.Map;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Data
public class Season {

    private double balance;

    private int level;
    private long exp;

    private SeasonQuest quests;
    private Skills skills;
    private Map<String, Long> stats;
    private Map<String, Integer> crates;
    private Map<String, Object> extensions;

}
