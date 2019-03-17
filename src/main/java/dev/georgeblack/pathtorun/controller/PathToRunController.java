package dev.georgeblack.pathtorun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathToRunController {
    Logger logger = LoggerFactory.getLogger(PathToRunController.class);

    @GetMapping("/test")
    public String testEndpoint() {
        return "All Good!";
    }
}
