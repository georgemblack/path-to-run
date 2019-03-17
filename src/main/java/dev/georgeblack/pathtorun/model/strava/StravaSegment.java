package dev.georgeblack.pathtorun.model.strava;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StravaSegment {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("climb_category")
    private int climbCategory;

    @JsonProperty("climb_category_desc")
    private String climbCategoryDescription;

    @JsonProperty("avg_grade")
    private double averageGrade;

    @JsonProperty("start_latlng")
    private List<Double> startCoordinate;

    @JsonProperty("end_latlng")
    private List<Double> endCoordinate;

    @JsonProperty("elev_difference")
    private double elevationDifference;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("points")
    private String encodedPolyline;
}
