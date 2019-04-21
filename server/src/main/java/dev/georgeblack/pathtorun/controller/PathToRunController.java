package dev.georgeblack.pathtorun.controller;

import dev.georgeblack.pathtorun.model.api.RoutesRequest;
import dev.georgeblack.pathtorun.model.api.RoutesResponse;
import dev.georgeblack.pathtorun.service.PathToRunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PathToRunController {
    Logger logger = LoggerFactory.getLogger(PathToRunController.class);

    @Autowired
    PathToRunService pathToRunService;

    @GetMapping(
        value = "/routes",
        produces = "application/json"
    )
    public RoutesResponse routes(RoutesRequest routesRequest) {
        return pathToRunService.getRoutes(routesRequest);
    }
}
