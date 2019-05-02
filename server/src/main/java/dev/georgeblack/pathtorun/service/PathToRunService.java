package dev.georgeblack.pathtorun.service;

import dev.georgeblack.pathtorun.model.Coordinate;
import dev.georgeblack.pathtorun.model.Region;
import dev.georgeblack.pathtorun.model.Route;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class PathToRunService {
  Logger logger = LoggerFactory.getLogger(PathToRunService.class);

  @Autowired StravaService stravaService;

  @Autowired StravaSegmentRepository stravaSegmentRepository;

  @Autowired RegionService regionService;

  @Autowired PolylineService polylineService;

  public RoutesResponse getRoutes(RoutesRequest routesRequest) {
    logger.info(String.format("Started new Path to Run request: %s", routesRequest));
    List<Route> routes = new ArrayList<>();

    Region region =
        regionService.buildRegionFromStartingPoint(
            routesRequest.getStartLat(), routesRequest.getStartLng(), routesRequest.getDistance());
    StravaSegments segments = stravaService.getSegmentsInRegion(region);

    // save to db in new thread
    new Thread(() -> saveStravaSegments(stravaSegmentRepository, segments)).start();

    segments.getSegments().forEach(segment -> {
      List<Coordinate> coordinates = polylineService.decodePolyline(segment.getEncodedPolyline());
      String id = Integer.toString(segment.getId());
      routes.add(new Route(id, coordinates));
    });

    return new RoutesResponse(region, routes);
  }

  private void saveStravaSegments(StravaSegmentRepository repository, StravaSegments segments) {
    segments.getSegments().forEach(segment -> repository.save(segment));
  }
}
