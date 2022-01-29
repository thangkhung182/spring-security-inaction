package com.trungnguyen.springsecurityinaction.controller;

import com.trungnguyen.springsecurityinaction.dto.UserDto;
import com.trungnguyen.springsecurityinaction.dto.UserMapper;
import com.trungnguyen.springsecurityinaction.entity.Otp;
import com.trungnguyen.springsecurityinaction.entity.User;
import com.trungnguyen.springsecurityinaction.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/user/add")
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userMapper.dtoToUser(userDto));
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody UserDto userDto) {
        userService.auth(userMapper.dtoToUser(userDto));
    }

    @PostMapping("/otp/check")
    public void validOtp(@RequestBody Otp otp, HttpServletResponse response) {
        if (userService.validOtp(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
