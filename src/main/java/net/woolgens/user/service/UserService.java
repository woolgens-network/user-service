package net.woolgens.user.service;

import io.quarkus.cache.CacheResult;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import net.woolgens.user.model.User;
import net.woolgens.user.model.dto.UserNameDto;
import net.woolgens.user.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    @CacheResult(cacheName = "user-small")
    public Map<String, String> getAllSmallProjectedUsers() {
        PanacheQuery<UserNameDto> query = repository.findAll().project(UserNameDto.class);

        Map<String, String> map = new HashMap<>();
        for(UserNameDto dto : query.list()) {
            map.put(dto.getName(), dto.getUuid());
        }
        return map;
    }

    public List<User> getAllSortedAndPaged(String sorted, String pageIndex, String pageSize) {
        PanacheQuery<User> query = repository.findAll(Sort.descending(sorted));
        if(pageIndex != null && pageSize != null) {
            try {
                query.page(Integer.valueOf(pageIndex), Integer.valueOf(pageSize));
            }catch (NumberFormatException ex) {}
        }
        return query.list();
    }

}
