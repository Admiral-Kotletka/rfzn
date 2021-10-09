package com.example.demo.controller;

import com.example.demo.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SocksController {

    @Autowired
    SocksService socksService;
}
