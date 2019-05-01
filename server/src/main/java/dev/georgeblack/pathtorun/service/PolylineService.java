package dev.georgeblack.pathtorun.service;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import dev.georgeblack.pathtorun.model.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** Utility class that decodes a Google polyline */
@Service
public class PolylineService {
  Logger logger = LoggerFactory.getLogger(PolylineService.class);

  public List<Coordinate> decodePolyline(String polyline) {
    // decode to Google LatLng
    List<LatLng> googleCoordinates = PolylineEncoding.decode(polyline);

    // convert to Coordinate
    List<Coordinate> coordinates = new ArrayList<>();
    for (LatLng c : googleCoordinates) {
      coordinates.add(new Coordinate(c.lat, c.lng));
    }

    logger.debug("Decoded polyline: " + polyline);
    return coordinates;
  }

  public String encodePolyline(List<Coordinate> coordinates) {
    LatLng[] googleCoordinates = new LatLng[coordinates.size()];
    for (int i = 0; i < coordinates.size(); i++) {
      Coordinate coordinate = coordinates.get(i);
      googleCoordinates[i] = new LatLng(coordinate.getLat(), coordinate.getLng());
    }

    return PolylineEncoding.encode(googleCoordinates);
  }
}
