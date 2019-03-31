package dev.georgeblack.pathtorun.controller;

import dev.georgeblack.pathtorun.service.PathToRunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path-to-run")
public class PathToRunController {
    Logger logger = LoggerFactory.getLogger(PathToRunController.class);

    @Autowired
    PathToRunService pathToRunService;

    @GetMapping(
        value = "/routes",
        produces = "application/json"
    )
    public String routes(
        @RequestParam("startLat") double startLat,
        @RequestParam("startLng") double startLng,
        @RequestParam("distance") int distance
    ) {
        return pathToRunService.start(startLat, startLng, distance);
    }
}
