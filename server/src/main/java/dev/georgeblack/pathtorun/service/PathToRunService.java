package dev.georgeblack.pathtorun.service;

import dev.georgeblack.pathtorun.model.Coordinate;
import dev.georgeblack.pathtorun.model.Region;
import dev.georgeblack.pathtorun.model.RunnablePath;
import dev.georgeblack.pathtorun.model.strava.StravaSegment;
import dev.georgeblack.pathtorun.model.strava.StravaSegments;
import dev.georgeblack.pathtorun.repository.RunnablePathRepository;
import dev.georgeblack.pathtorun.repository.StravaSegmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathToRunService {
    Logger logger = LoggerFactory.getLogger(PathToRunService.class);

    @Autowired
    StravaSegmentRepository stravaSegmentRepository;

    @Autowired
    PolylineService polylineService;

    @Autowired
    RunnablePathRepository repository;

    @Autowired
    RegionService regionService;

    public String start(double startLat, double startLng, int distance) {
        Coordinate start = new Coordinate(startLat, startLng);
        logger.info(String.format("Started new Path to Run request from %s, for %s miles", start, distance));

        Region region = regionService.buildRegionFromStartingPoint(start, distance);
        StravaSegments segments = stravaSegmentRepository.getSegmentsInRegion(region);

        for (StravaSegment segment: segments.getSegments()) {
            List<Coordinate> coordinates = polylineService.decodePolyline(segment.getEncodedPolyline());
            RunnablePath runnablePath = new RunnablePath(coordinates, segment.getEncodedPolyline());
            repository.save(runnablePath);
        }

        return "All good!";
    }
}
