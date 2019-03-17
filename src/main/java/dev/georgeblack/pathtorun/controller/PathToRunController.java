package dev.georgeblack.pathtorun.controller;

import dev.georgeblack.pathtorun.service.PathToRunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathToRunController {
    Logger logger = LoggerFactory.getLogger(PathToRunController.class);

    @Autowired
    PathToRunService pathToRunService;

    @GetMapping("/test")
    public String testEndpoint() {
        pathToRunService.test();
        return "All Good!";
    }
}
