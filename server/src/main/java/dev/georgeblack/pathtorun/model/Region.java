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
  private Coordinate center;

  public Region(double[] coordinates) {
    this.southwest = new Coordinate(coordinates[0], coordinates[1]);
    this.northeast = new Coordinate(coordinates[2], coordinates[3]);
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
        + northeast.getLat()
        + ", "
        + northeast.getLng()
        + ")]";
  }
}
