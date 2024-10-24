package com.api.redis.controllers;


import com.api.redis.dao.UserDao;
import com.api.redis.models.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserDao userDao;


    // creating users
    @PostMapping
    public Users create(@RequestBody Users users){

        users.setUserId(UUID.randomUUID().toString());
        return userDao.save(users);
    }

    @GetMapping("/{userId}")
    public Users getUser(@PathVariable("userId") String userId){
        return userDao.get(userId);
    }


//    // find all the users
//    @GetMapping
//    public Map<Object , Object> findAll(){
//        return userDao.findAll();
//    }


    // instead of getting in map , here i am fetching the users as a list
    @GetMapping
    public List<Users> findAll(){
        Map<Object , Object> all = userDao.findAll();

        Collection<Object> values = all.values();

        return values.stream().map(value -> (Users)value).collect(Collectors.toList());
    }


    // delete user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
        userDao.delete(userId);
    }







}
