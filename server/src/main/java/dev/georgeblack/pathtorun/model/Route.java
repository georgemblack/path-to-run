package dev.georgeblack.pathtorun.model.api;

import dev.georgeblack.pathtorun.model.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Route {
    private List<Coordinate> coordinates;
}
