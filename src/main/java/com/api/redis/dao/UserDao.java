package com.api.redis.dao;


import com.api.redis.models.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {


    @Autowired
    private RedisTemplate<String , Object> redisTemplate;

        private static final String KEY = "USER";


        // for saving the user
        public Users save(Users users){
            redisTemplate.opsForHash().put(KEY , users.getUserId() , users);
            return users;
        }

        // for getting the user object using user Id
        public Users get(String userId){
            return (Users) redisTemplate.opsForHash().get(KEY , userId);
        }

        // this method will return all the user object
    public Map<Object , Object> findAll(){
            return redisTemplate.opsForHash().entries(KEY);
    }

    // for deleting the user
    public void delete(String userId){
            redisTemplate.opsForHash().delete(KEY , userId);
    }


    // for updating the user object based on the KEY , and user ID
    public Users update(Users users) {
        // Check if the user exists in Redis
        Users existingUser = (Users) redisTemplate.opsForHash().get(KEY, users.getUserId());

        if (existingUser != null) {
            // If user exists, update the details by overwriting with new data
            redisTemplate.opsForHash().put(KEY, users.getUserId(), users);
            return users;
        } else {
            // If the user doesn't exist, handle the scenario (optional)
            throw new RuntimeException("User not found with ID: " + users.getUserId());
        }
    }




}
