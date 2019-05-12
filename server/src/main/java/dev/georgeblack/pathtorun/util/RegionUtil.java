package dev.georgeblack.pathtorun.util;

import dev.georgeblack.pathtorun.model.Coordinate;
import dev.georgeblack.pathtorun.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegionUtil {
  public static final double ONE_MILE_IN_LATITUDE_DEGREES = (1.0 / 69.0);
  public static final double HALF_MILE_IN_LATITUDE_DEGREES = (ONE_MILE_IN_LATITUDE_DEGREES / 2.0);
  public static final double ONE_DEGREE_LONGITUDE_IN_MILES_AT_EQUATOR = 69.172;
  private static Logger logger = LoggerFactory.getLogger(RegionUtil.class);

  /**
   * Given latitude/longitude of a starting point, build a bounding box that is X miles
   * North/South/East/West of point. Region is defined by two coordinates: the South-West and
   * North-East corner of bounding box.
   */
  public static Region buildRegionFromStartingPoint(Coordinate start, int distance) {
    double oneMileInLongitudeDegrees =
        (1.0
            / (Math.cos(Math.toRadians(start.getLat()))
                * ONE_DEGREE_LONGITUDE_IN_MILES_AT_EQUATOR));

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

  /**
   * Given a single region, split it into smaller regions that are one square mile, with 1/2 mile
   * overlap. This is so we can call the Strava Explore Segments API on each smaller region, getting
   * better coverage of segments.
   *
   * <p>If the region crosses the Prime Meridian, I'm not sure this code will work. That's fine.
   */
  public static List<Region> splitRegion(Region initialRegion, int regionWidth, int regionHeight) {
    List<Region> splitRegions = new LinkedList<>();

    // break region into overlapping cols/rows
    int numColumns = (regionWidth * 2) - 1;
    int numRows = (regionHeight * 2) - 1;

    // start in southwest corner
    // create rows of new regions, each row 1 mile tall
    // each new row starts 1/2 mile north of the previous row, so they will overlap
    Coordinate rowSouthwestCorner = new Coordinate(initialRegion.getSouthwest());
    for (int i = 0; i < numRows; i++) {

      // start in southwest corner of row
      // create 1x1 mile regions, each region shifted 1/2 east for overlap
      Coordinate columnSouthwestCorner = new Coordinate(rowSouthwestCorner);
      for (int j = 0; j < numColumns; j++) {
        // calculate southwest corner
        Coordinate newSouthwestCorner = new Coordinate(columnSouthwestCorner);

        // calculate northeast corner
        double northLat = newSouthwestCorner.getLat() + ONE_MILE_IN_LATITUDE_DEGREES;
        double eastLng =
            newSouthwestCorner.getLng() + calcOneMileDegreesLng(newSouthwestCorner.getLat());
        Coordinate newNortheastCorner = new Coordinate(northLat, eastLng);

        // calculate center
        double centerLat = newSouthwestCorner.getLat() + HALF_MILE_IN_LATITUDE_DEGREES;
        double centerLng =
            newSouthwestCorner.getLng() + calcHalfMileDegreesLng(newSouthwestCorner.getLat());
        Coordinate center = new Coordinate(centerLat, centerLng);

        splitRegions.add(new Region(newSouthwestCorner, newNortheastCorner, center));

        // shift southwest corner east by 1/2 mile
        columnSouthwestCorner.setLng(
            columnSouthwestCorner.getLng()
                + calcHalfMileDegreesLng(columnSouthwestCorner.getLat()));
      }

      // shift southwest corner north by 1/2 mile
      rowSouthwestCorner.setLat(rowSouthwestCorner.getLat() + HALF_MILE_IN_LATITUDE_DEGREES);
    }

    return splitRegions;
  }

  /** Given the current latitude, calculate one mile in degrees longitude. */
  private static double calcOneMileDegreesLng(double currentLat) {
    return (1.0
        / (Math.cos(Math.toRadians(currentLat)) * ONE_DEGREE_LONGITUDE_IN_MILES_AT_EQUATOR));
  }

  /** Given the current latitude, calculate 1/2 mile in degrees longitude. */
  private static double calcHalfMileDegreesLng(double currentLat) {
    return calcOneMileDegreesLng(currentLat) / 2.0;
  }
}
