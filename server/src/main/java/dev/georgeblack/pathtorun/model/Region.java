package dev.georgeblack.pathtorun.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Region {
    private double southwestLat;
    private double southwestLon;
    private double northeastLat;
    private double northeastLon;

    public Region(double[] coordinates) {
        this.southwestLat = coordinates[0];
        this.southwestLon = coordinates[1];
        this.northeastLat = coordinates[2];
        this.northeastLon = coordinates[3];
    }

    public String getBoundsAsDelimitedString() {
        return southwestLat + "," + southwestLon + "," + northeastLat + "," + northeastLon;
    }

    @Override
    public String toString() {
        return "[(" + southwestLat + ", " + southwestLon + "), (" + northeastLat + ", " + northeastLon + ")]";
    }
}
