package com.itmo.lab4.backend.controllers;

import com.itmo.lab4.backend.database.UserRepository;
import com.itmo.lab4.backend.database.entities.User;
import com.itmo.lab4.backend.security.JwtTokenProvider;
import com.itmo.lab4.backend.security.exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> signin(@RequestBody AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            //String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
            String token = jwtTokenProvider.createToken(username, new ArrayList<String>() {{
                add("User");
            }});
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password");
        }
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody AuthenticationRequest data) {
        String username = data.getUsername();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistException();
        }
        userRepository.save(User.builder().username(username)
                .password(new BCryptPasswordEncoder().encode(data.getPassword())).build());

        return signin(data);
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class AuthenticationRequest {

    private String username;
    private String password;
}
