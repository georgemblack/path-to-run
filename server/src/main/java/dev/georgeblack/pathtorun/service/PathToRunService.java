package dev.georgeblack.pathtorun.service;

import dev.georgeblack.pathtorun.model.Coordinate;
import dev.georgeblack.pathtorun.model.Region;
import dev.georgeblack.pathtorun.model.Route;
import dev.georgeblack.pathtorun.model.api.RoutesRequest;
import dev.georgeblack.pathtorun.model.api.RoutesResponse;
import dev.georgeblack.pathtorun.model.strava.StravaSegment;
import dev.georgeblack.pathtorun.model.strava.StravaSegments;
import dev.georgeblack.pathtorun.repository.StravaSegmentRepository;
import dev.georgeblack.pathtorun.util.PolylineUtil;
import dev.georgeblack.pathtorun.util.RegionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class PathToRunService {
  private Logger logger = LoggerFactory.getLogger(PathToRunService.class);

  @Autowired private StravaService stravaService;

  @Autowired private StravaSegmentRepository stravaSegmentRepository;

  public RoutesResponse getRoutes(RoutesRequest routesRequest) {
    logger.info(String.format("Started new Path to Run request: %s", routesRequest));
    long start = System.currentTimeMillis();

    List<Route> routes = new ArrayList<>();
    List<StravaSegment> segments = new ArrayList<>();

    Coordinate startingPoint =
        new Coordinate(routesRequest.getStartLat(), routesRequest.getStartLng());
    Region initialRegion =
        RegionUtil.buildRegionFromStartingPoint(startingPoint, routesRequest.getDistance());

    List<Region> splitRegions =
        RegionUtil.splitRegion(
            initialRegion, routesRequest.getDistance(), routesRequest.getDistance());

    // for each split region, get segments
    List<Callable<StravaSegments>> callables = new LinkedList<>();
    for (Region splitRegion : splitRegions) {
      callables.add(() -> stravaService.getSegmentsInRegion(splitRegion));
    }

    logger.info(String.format("Submitting %d tasks to Strava service...", callables.size()));
    ExecutorService executorService = Executors.newFixedThreadPool(callables.size());

    try {
      List<Future<StravaSegments>> futures = executorService.invokeAll(callables);
      for (Future<StravaSegments> future : futures) {
        StravaSegments stravaSegments = future.get();
        segments.addAll(stravaSegments.getSegments());
      }
    } catch (Exception e) {
      logger.error(
          String.format("Error with task submitted to Strava service: %s", e.getMessage()));
    }

    executorService.shutdown();

    // save to db in new thread
    new Thread(() -> saveStravaSegments(stravaSegmentRepository, segments)).start();

    // build route from each segment
    segments.forEach(
        segment -> {
          List<Coordinate> coordinates = PolylineUtil.decodePolyline(segment.getEncodedPolyline());
          String id = Integer.toString(segment.getId());
          routes.add(new Route(id, coordinates));
        });

    long elapsed = System.currentTimeMillis() - start;
    logger.info(String.format("Path to Run service completed in %d milliseconds", elapsed));

    return new RoutesResponse(initialRegion, routes);
  }

  private void saveStravaSegments(
      StravaSegmentRepository repository, List<StravaSegment> segments) {
    segments.forEach(repository::save);
  }
}
