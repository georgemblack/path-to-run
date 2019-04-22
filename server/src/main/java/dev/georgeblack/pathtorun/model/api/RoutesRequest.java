package dev.georgeblack.pathtorun.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoutesRequest {
  private double startLat;
  private double startLng;
  private int distance;
  private String shape;

  public String toString() {
    return "{start: ["
        + this.startLat
        + ", "
        + this.startLng
        + "], distance: "
        + this.distance
        + ", "
        + "shape: "
        + this.shape
        + "}";
  }
}
