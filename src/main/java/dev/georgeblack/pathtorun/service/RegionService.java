package dev.georgeblack.pathtorun.service;

import dev.georgeblack.pathtorun.model.Coordinate;
import dev.georgeblack.pathtorun.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegionService {
    Logger logger = LoggerFactory.getLogger(RegionService.class);

    public static final double ONE_MILE_IN_LATITUDE_DEGREES = (1.0 / 69.0);
    public static final double ONE_DEGREE_LONGITUDE_IN_MILES_AT_EQUATOR = 69.172;

    /**
     * Given latitude/longitude of a starting point, build a bounding box that is X miles North/South/East/West of
     * point. Region is defined by two coordinates: the South-West and North-East corner of bounding box. bounding box.
     */
    public Region buildRegionFromStartingPoint(Coordinate start, int distance) {
        double oneMileInLongitudeDegrees =
            (1.0 / (Math.cos(Math.toRadians(start.getLat())) * ONE_DEGREE_LONGITUDE_IN_MILES_AT_EQUATOR));

        double northLat = start.getLat() + (distance * ONE_MILE_IN_LATITUDE_DEGREES);
        double southLat = start.getLat() - (distance * ONE_MILE_IN_LATITUDE_DEGREES);

        double eastLng = start.getLon() + (distance * oneMileInLongitudeDegrees);
        double westLng = start.getLon() - (distance * oneMileInLongitudeDegrees);

        Region region = new Region(southLat, westLng, northLat, eastLng);
        logger.info("Generated region: " + region);
        return region;
    }
}
