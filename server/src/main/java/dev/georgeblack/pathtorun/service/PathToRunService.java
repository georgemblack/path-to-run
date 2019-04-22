package dev.georgeblack.pathtorun.service;

import dev.georgeblack.pathtorun.model.Region;
import dev.georgeblack.pathtorun.model.api.RoutesRequest;
import dev.georgeblack.pathtorun.model.api.RoutesResponse;
import dev.georgeblack.pathtorun.model.strava.StravaSegment;
import dev.georgeblack.pathtorun.model.strava.StravaSegments;
import dev.georgeblack.pathtorun.repository.RunnablePathRepository;
import dev.georgeblack.pathtorun.repository.StravaSegmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathToRunService {
  Logger logger = LoggerFactory.getLogger(PathToRunService.class);

  @Autowired StravaService stravaService;

  @Autowired StravaSegmentRepository stravaSegmentRepository;

  @Autowired RegionService regionService;

  public RoutesResponse getRoutes(RoutesRequest routesRequest) {
    logger.info(String.format("Started new Path to Run request: %s", routesRequest));

    Region region =
        regionService.buildRegionFromStartingPoint(
            routesRequest.getStartLat(), routesRequest.getStartLng(), routesRequest.getDistance());
    StravaSegments segments = stravaService.getSegmentsInRegion(region);

    for (StravaSegment segment : segments.getSegments()) {
      stravaSegmentRepository.save(segment);
    }

    return new RoutesResponse("Route response status");
  }
}
