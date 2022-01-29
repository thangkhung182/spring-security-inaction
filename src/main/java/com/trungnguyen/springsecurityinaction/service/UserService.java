package com.trungnguyen.springsecurityinaction.service;

import com.trungnguyen.springsecurityinaction.entity.Otp;
import com.trungnguyen.springsecurityinaction.entity.User;

public interface UserService {
    void addUser(User user);

    void auth(User user);

    void renewOtp(User user);

    boolean validOtp(Otp otp);
}
