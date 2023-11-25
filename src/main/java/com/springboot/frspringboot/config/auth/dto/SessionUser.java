package com.springboot.frspringboot.config.auth.dto;

import com.springboot.frspringboot.domain.user.User;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}

// 인증된 사용자 정보만 필요하므로 세가지 필드만 가진다.

