package com.itmo.lab4.backend.controllers;

import com.itmo.lab4.backend.database.PointRepository;
import com.itmo.lab4.backend.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity getAll(@PathVariable("username") String username, Authentication auth){
        return doAccess(username, auth,
                () -> ok(userRepository.findByUsername(username).getPoints()));
    }

    interface MakeResponse{
        ResponseEntity make();
    }

    private ResponseEntity doAccess(String username, Authentication auth, MakeResponse response){
        if(!username.equals(auth.getName())){
            return new ResponseEntity("Вы вошли как другой пользователь", HttpStatus.FORBIDDEN);
        }
        return response.make();
    }

    @GetMapping(path = "/{username}/points/{point_id}")
    public ResponseEntity getPoint(@PathVariable("username") String username,
                                   @PathVariable("point_id") Long pointId,
                                   Authentication auth){
        return doAccess(username, auth, new MakeResponse() {
            public ResponseEntity make() {
                return ok(userRepository.findByUsername(username).getPoints().stream().
                        filter(i -> i.getId().compareTo(pointId) == 0).collect(Collectors.toList()).get(0));
            }
        });
    }
}
