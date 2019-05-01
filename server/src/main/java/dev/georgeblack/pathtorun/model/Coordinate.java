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

  @Override
  public String toString() {
    return "(" + lat + ", " + lng + ")";
  }
}
