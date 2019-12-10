package com.itmo.lab4.backend.controllers;

import com.itmo.lab4.backend.database.PointRepository;
import com.itmo.lab4.backend.database.UserRepository;
import com.itmo.lab4.backend.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/user")
public class PointsController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/{username}/points")
    public ResponseEntity getAll(@PathVariable("username") String username,
                                 @AuthenticationPrincipal UserDetails userDetails){
        return doAccess(username, userDetails,
                User::getPoints);
    }

    interface MakeResponse{
        Object make(User user);
    }

    private ResponseEntity doAccess(String username, UserDetails userDetails, MakeResponse response){
        if(!username.equals(userDetails.getUsername())){
            return new ResponseEntity("Вы вошли как другой пользователь", HttpStatus.FORBIDDEN);
        }
        User user = userRepository.findByUsername(username).
                orElseThrow(() ->
                        new UsernameNotFoundException("Username " + username + "not found"));
        return ok(response.make(user));
    }

    @GetMapping(path = "/{username}/points/{point_id}")
    public ResponseEntity getPoint(@PathVariable("username") String username,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   @PathVariable("point_id") Long pointId){
        return doAccess(username, userDetails,
                (user) -> user.getPoints().stream().
                filter(i -> i.getId().compareTo(pointId) == 0).collect(Collectors.toList()).get(0));
    }
}
