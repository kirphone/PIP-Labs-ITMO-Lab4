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
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public ResponseEntity getAll(@PathVariable("username") String username){
        return ok(userRepository.findByUsername(username).get().getPoints());
    }

    @GetMapping(path = "/{username}/points/{point_id}")
    public ResponseEntity getPoint(@PathVariable("username") String username,
                                   @PathVariable("point_id") Long pointId){
        try {
            return ok(userRepository.findByUsername(username).get().getPoints().stream().
                    filter(i -> i.getId().compareTo(pointId) == 0)
                    .collect(Collectors.toList()).get(0));
        } catch (IndexOutOfBoundsException ex){
            return new ResponseEntity("Point does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/{username}/points")
    public ResponseEntity addPoint(@PathVariable("username") String username,
                                   @RequestBody PointRequest point){
        User user = userRepository.findByUsername(username).get();
        long pointId = pointRepository.save(PointEntity.builder().xcoord(point.getX()).ycoord(point.getY())
        .radius(point.getRadius()).user(user).requestDate(new Date()).isHit("YES").build()).getId();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/" + pointId);
        return new ResponseEntity("Point successfully created", responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{username}/points")
    public ResponseEntity deletePoints(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username).get();
        pointRepository.deleteByUser(user);
        return ok("Points successfully deleted");
    }}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class PointRequest{

    private Double x;
    private Double y;
    private Double radius;
}
