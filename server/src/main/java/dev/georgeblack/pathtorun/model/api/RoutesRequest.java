package dev.georgeblack.pathtorun.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoutesRequest {
    private String startLat;
    private String startLng;
    private String distance;
    private String shape;
}
