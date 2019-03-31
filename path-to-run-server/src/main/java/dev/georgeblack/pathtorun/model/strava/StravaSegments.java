package dev.georgeblack.pathtorun.model.strava;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StravaSegments {
    private List<StravaSegment> segments;
}
