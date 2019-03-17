package dev.georgeblack.pathtorun.service;

import dev.georgeblack.pathtorun.model.Region;
import dev.georgeblack.pathtorun.model.strava.StravaSegments;
import dev.georgeblack.pathtorun.repository.StravaSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathToRunService {

    @Autowired
    StravaSegmentRepository stravaSegmentRepository;

    public void test() {
        double[] bounds = {41.868794, -87.653169, 41.900912, -87.603218};
        StravaSegments segments = stravaSegmentRepository.getSegmentsInRegion(new Region(bounds));
    }
}
