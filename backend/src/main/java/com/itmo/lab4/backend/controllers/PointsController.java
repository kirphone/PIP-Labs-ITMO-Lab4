package com.itmo.lab4.backend.controllers;

import com.itmo.lab4.backend.database.PointRepository;
import com.itmo.lab4.backend.database.UserRepository;
import com.itmo.lab4.backend.database.entities.PointEntity;
import com.itmo.lab4.backend.database.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/user")
public class PointsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PointRepository pointRepository;

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
        Optional<User> user = doAccess(username, userDetails);
        return user.map(value -> ok(response.make(value))).orElseGet(() ->
                new ResponseEntity("Вы вошли как другой пользователь", HttpStatus.FORBIDDEN));
    }

    private Optional<User> doAccess(String username, UserDetails userDetails){
        if(!username.equals(userDetails.getUsername())){
            return Optional.empty();
        }
        User user = userRepository.findByUsername(username).
                orElseThrow(() ->
                        new UsernameNotFoundException("Username " + username + "not found"));
        return Optional.of(user);
    }

    @GetMapping(path = "/{username}/points/{point_id}")
    public ResponseEntity getPoint(@PathVariable("username") String username,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   @PathVariable("point_id") Long pointId){
        return doAccess(username, userDetails,
                (user) -> user.getPoints().stream().
                filter(i -> i.getId().compareTo(pointId) == 0).collect(Collectors.toList()).get(0));
    }

    @PostMapping(path = "/{username}/points")
    public ResponseEntity addPoint(@PathVariable("username") String username,
                            @AuthenticationPrincipal UserDetails userDetails,
                                   @RequestBody PointRequest point){
        Optional<User> user = doAccess(username, userDetails);
        if(!user.isPresent()){
            return new ResponseEntity("Вы вошли как другой пользователь", HttpStatus.FORBIDDEN);
        }
        long pointId = pointRepository.save(PointEntity.builder().xcoord(point.getX()).ycoord(point.getY())
        .radius(point.getRadius()).user(user.get()).requestDate(new Date()).isHit("YES").build()).getId();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/" + pointId);
        return new ResponseEntity(responseHeaders, HttpStatus.CREATED);
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class PointRequest{

    private double x;
    private double y;
    private double radius;
}
