package com.Ecommerce.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.DTO.LoginRequest;
import com.Ecommerce.Model.User;
import com.Ecommerce.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	

	    private final AuthService service;
	    
	    public  AuthController(AuthService service)
	    {
	    	this.service=service;
	    }

	    @PostMapping("/register")
	    public String register(@RequestBody User user) {
	        return service.register(user);
	    }

   
          @PostMapping("/login")
          public String login(@RequestBody LoginRequest request) {
              return service.login(request.getEmail(), request.getPassword());
          
}
}


