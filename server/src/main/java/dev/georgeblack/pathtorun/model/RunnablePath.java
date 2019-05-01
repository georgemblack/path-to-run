package dev.georgeblack.pathtorun.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "runnable_paths")
public class RunnablePath {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private double startLat;
  private double startLon;
  private double endLat;
  private double endLon;
  private String polyline;

  public RunnablePath(List<Coordinate> coordinates, String polyline) {
    this.startLat = coordinates.get(0).getLat();
    this.startLon = coordinates.get(0).getLng();
    this.endLat = coordinates.get(coordinates.size() - 1).getLat();
    this.endLon = coordinates.get(coordinates.size() - 1).getLng();
    this.polyline = polyline;
  }
}
