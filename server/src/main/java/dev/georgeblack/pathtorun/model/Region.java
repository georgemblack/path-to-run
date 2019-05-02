package dev.georgeblack.pathtorun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Region {
  private double southwestLat;
  private double southwestLng;
  private double northeastLat;
  private double northeastLng;

  public Region(double[] coordinates) {
    this.southwestLat = coordinates[0];
    this.southwestLng = coordinates[1];
    this.northeastLat = coordinates[2];
    this.northeastLng = coordinates[3];
  }

  @JsonIgnore
  public String getBoundsAsDelimitedString() {
    return southwestLat + "," + southwestLng + "," + northeastLat + "," + northeastLng;
  }

  @Override
  public String toString() {
    return "[("
        + southwestLat
        + ", "
        + southwestLng
        + "), ("
        + northeastLat
        + ", "
        + northeastLng
        + ")]";
  }
}
