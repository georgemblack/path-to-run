package dev.georgeblack.pathtorun.util;

import dev.georgeblack.pathtorun.model.Coordinate;
import dev.georgeblack.pathtorun.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionUtil {
  static Logger logger = LoggerFactory.getLogger(RegionUtil.class);

  public static final double ONE_MILE_IN_LATITUDE_DEGREES = (1.0 / 69.0);
  public static final double ONE_DEGREE_LONGITUDE_IN_MILES_AT_EQUATOR = 69.172;

  /**
   * Given latitude/longitude of a starting point, build a bounding box that is X miles
   * North/South/East/West of point. Region is defined by two coordinates: the South-West and
   * North-East corner of bounding box.
   */
  public static Region buildRegionFromStartingPoint(
          Coordinate start, int distance) {
    double oneMileInLongitudeDegrees =
        (1.0 / (Math.cos(Math.toRadians(start.getLat())) * ONE_DEGREE_LONGITUDE_IN_MILES_AT_EQUATOR));

    double northLat = start.getLat() + (distance * ONE_MILE_IN_LATITUDE_DEGREES);
    double southLat = start.getLat() - (distance * ONE_MILE_IN_LATITUDE_DEGREES);

    double eastLng = start.getLng() + (distance * oneMileInLongitudeDegrees);
    double westLng = start.getLng() - (distance * oneMileInLongitudeDegrees);

    Coordinate southwest = new Coordinate(southLat, westLng);
    Coordinate northeast = new Coordinate(northLat, eastLng);

    Region region = new Region(southwest, northeast, start);
    logger.info("Generated region: " + region);
    return region;
  }
}
