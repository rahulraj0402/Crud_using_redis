package com.api.redis.models;


import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users implements Serializable {

    private String userId;
    private String name;
    private String phone;
    private String email;

}
