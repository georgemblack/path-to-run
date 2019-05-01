package dev.georgeblack.pathtorun.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LatLng {
    private double lat;
    private double lng;

    public LatLng(double[] points) {
        this.lat = points[0];
        this.lng = points[1];
    }
}
