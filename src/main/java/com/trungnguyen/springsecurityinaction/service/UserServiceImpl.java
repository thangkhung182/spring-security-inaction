package com.trungnguyen.springsecurityinaction.service;

import com.trungnguyen.springsecurityinaction.entity.Otp;
import com.trungnguyen.springsecurityinaction.entity.User;
import com.trungnguyen.springsecurityinaction.repository.OtpRepository;
import com.trungnguyen.springsecurityinaction.repository.UserRepository;
import com.trungnguyen.springsecurityinaction.util.GenerateCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final OtpRepository otpRepository;

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void auth(User user) {
        var dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Bad credentials"));

        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            renewOtp(user);
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    public void renewOtp(User user) {
        String code = GenerateCodeUtil.generateCode();
        var otp = otpRepository.findByUsername(user.getUsername())
                .orElse(new Otp(user.getUsername(), code));

        otpRepository.save(otp);
    }

    @Override
    public boolean validOtp(Otp otp) {
        var userOtp = otpRepository.findByUsername(otp.getUsername());
        return userOtp.isPresent()
                && userOtp.get().getCode().equals(otp.getCode());
    }
}
