package net.woolgens.user.model;

import lombok.Data;

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

    private Map<String, Long> quests;
    private Map<String, Long> stats;
    private Map<String, Integer> crates;
}
