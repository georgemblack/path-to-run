package dev.georgeblack.pathtorun.service;

import dev.georgeblack.pathtorun.model.Region;
import dev.georgeblack.pathtorun.model.strava.StravaSegment;
import dev.georgeblack.pathtorun.model.strava.StravaSegments;
import dev.georgeblack.pathtorun.repository.StravaSegmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathToRunService {
    Logger logger = LoggerFactory.getLogger(PathToRunService.class);

    @Autowired
    StravaSegmentRepository stravaSegmentRepository;

    @Autowired
    PolylineService polylineService;

    public String getAllCoordinatesForRegion() {
        double[] bounds = {41.868794, -87.653169, 41.900912, -87.603218};
        StravaSegments segments = stravaSegmentRepository.getSegmentsInRegion(new Region(bounds));
        StringBuilder sb = new StringBuilder();
        for (StravaSegment segment : segments.getSegments()) {
            sb.append(polylineService.decodePolyline(segment.getEncodedPolyline()).toString());
        }
        return sb.toString();
    }
}
