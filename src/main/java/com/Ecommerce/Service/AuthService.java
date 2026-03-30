package com.Ecommerce.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Ecommerce.Model.User;
import com.Ecommerce.Repository.UserRepository;
import com.Ecommerce.Security.JwtUtil;


@Service
public class AuthService {
	
	  private final UserRepository userRepository;
	    private final PasswordEncoder encoder;
	    private final JwtUtil jwtUtil;
	
	public AuthService(UserRepository userRepository,
            PasswordEncoder encoder,
            JwtUtil jwtUtil) {
this.userRepository = userRepository;
this.encoder = encoder;
this.jwtUtil = jwtUtil;
}

  

    public String register(User user) {

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return  jwtUtil.generateToken(email);
        
        
    }
}