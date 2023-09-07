package com.sandeep.springredisdemo.repository.impl;

import com.sandeep.springredisdemo.model.User;
import com.sandeep.springredisdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final String KEY = "User";

    @Autowired
    public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean save(User user) {
        try {
            redisTemplate.opsForHash().put(KEY, user.getId(), user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Cacheable(value = "usersListCache") // Cache expires after 1 hour
//    @CacheEvict(value = "productCache", key = "#productId", = 20)
    public List<User> fetchAllUsers() {
        List<Object> objectList;
        objectList = redisTemplate.opsForHash().values(KEY);
        return objectList.stream()
                .filter(obj -> obj instanceof User)
                .map(User.class::cast)
                .toList();
    }

    @Override
    public User getUserById(Integer id) {
        return ((User) redisTemplate.opsForHash().get(KEY, id));
    }


}
