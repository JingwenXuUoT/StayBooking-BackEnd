package com.laioffer.staybooking.authentication;

import com.laioffer.staybooking.model.LoginRequest;
import com.laioffer.staybooking.model.LoginResponse;
import com.laioffer.staybooking.model.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationContoller {
    private final AuthenticationService authenticationService;
    public AuthenticationContoller(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest body) {
        authenticationService.register(body.username(), body.password(), body.role());
    }//write operation won't put query params in path for seurity,so we need request entity
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest body) {
        String token = authenticationService.login(body.username(), body.password());
        return new LoginResponse(token);
    }
}
