package dev.georgeblack.pathtorun.service;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import dev.georgeblack.pathtorun.model.Coordinate;
import dev.georgeblack.pathtorun.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that decodes a Google polyline
 */
@Service
public class PolylineService {
    Logger logger = LoggerFactory.getLogger(PolylineService.class);

    public Route decodePolyline(String polyline) {

        // decode to Google LatLng
        List<LatLng> googleCoordinates = PolylineEncoding.decode(polyline);

        // convert to Coordinate
        List<Coordinate> coordinates = new ArrayList<>();
        for (LatLng c : googleCoordinates) {
            coordinates.add(new Coordinate(c.lat, c.lng));
        }

        logger.debug("Decoded polyline: " + polyline);
        return new Route(coordinates);
    }
}
