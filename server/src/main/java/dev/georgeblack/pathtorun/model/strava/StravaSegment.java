package dev.georgeblack.pathtorun.model.strava;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * A single Strava Segment. This class is used to deserialize data from Strava API, and map data to
 * db entity.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "strava_segments")
public class StravaSegment {
  @Id private int id;
  private String name;
  private double startCoordinateLat;
  private double startCoordinateLng;
  private double endCoordinateLat;
  private double endCoordinateLng;
  private double distance;

  @Column(columnDefinition = "text")
  private String encodedPolyline;

  /** Map Strava API response */
  @JsonCreator
  public StravaSegment(
      @JsonProperty("id") int id,
      @JsonProperty("name") String name,
      @JsonProperty("start_latlng") List<Double> startCoordinate,
      @JsonProperty("end_latlng") List<Double> endCoordinate,
      @JsonProperty("distance") double distance,
      @JsonProperty("points") String encodedPolyline) {

    this.id = id;
    this.name = name;
    this.startCoordinateLat = startCoordinate.get(0);
    this.startCoordinateLng = startCoordinate.get(1);
    this.endCoordinateLat = endCoordinate.get(0);
    this.endCoordinateLng = endCoordinate.get(1);
    this.distance = distance;
    this.encodedPolyline = encodedPolyline;
  }
}
