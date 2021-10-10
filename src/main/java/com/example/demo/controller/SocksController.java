package com.example.demo.controller;

import com.example.demo.entity.Socks;
import com.example.demo.service.SocksService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SocksController {

    @Autowired
    SocksService socksService;

    @PostMapping("/socks/income")
    public ResponseEntity<String> socksIncome(@Valid @RequestBody Socks socks) throws NotFoundException {
        if (socksService.isAlreadyExist(socks)) {
            socksService.updateQuantityIncome(socks);
        } else {
            socksService.addSocks(socks);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/socks/outcome")
    public ResponseEntity<String> socksOutcome(@Valid @RequestBody Socks socks) throws NotFoundException {
        if (!socksService.isAlreadyExist(socks)) {
            return new ResponseEntity<>("no such Entity in database", HttpStatus.BAD_REQUEST);
        }
        if (socksService.isEnoughQuantity(socks)) {
            socksService.updateQuantityOutcome(socks);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("don't have such amount of socks", HttpStatus.BAD_REQUEST);
        }
    }
}