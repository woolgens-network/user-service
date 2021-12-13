package net.woolgens.user.model;

import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Data
public class SeasonQuest {

    private Map<String, Map<String, Long>> selected;
    private Set<String> finished;
}
