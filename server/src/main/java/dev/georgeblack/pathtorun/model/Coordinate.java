package dev.georgeblack.pathtorun.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinate {
  private double lat;
  private double lng;

  /**
   * Constructs new coordinate by cloning existing coordinate.
   *
   * @param existingCoordinate
   */
  public Coordinate(Coordinate existingCoordinate) {
    this.lat = existingCoordinate.getLat();
    this.lng = existingCoordinate.getLng();
  }

  @Override
  public String toString() {
    return "(" + lat + ", " + lng + ")";
  }
}
