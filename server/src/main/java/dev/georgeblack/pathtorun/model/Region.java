package dev.georgeblack.pathtorun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Region {
  private Coordinate southwest;
  private Coordinate northeast;

  public Region(double[] coordinates) {
    this.southwest = new Coordinate(coordinates[0], coordinates[1]);
    this.northeast = new Coordinate(coordinates[2], coordinates[3]);
  }

  public Region(
      double southwestLat, double southwestLng, double northeastLat, double northeastLng) {
    this.southwest = new Coordinate(southwestLat, southwestLng);
    this.northeast = new Coordinate(northeastLat, northeastLng);
  }

  @JsonIgnore
  public String getBoundsAsDelimitedString() {
    return southwest.getLat()
        + ","
        + southwest.getLng()
        + ","
        + northeast.getLat()
        + ","
        + northeast.getLng();
  }

  @Override
  public String toString() {
    return "[("
        + southwest.getLat()
        + ", "
        + southwest.getLng()
        + "), ("
        + southwest.getLat()
        + ", "
        + southwest.getLng()
        + ")]";
  }
}
