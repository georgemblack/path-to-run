package dev.georgeblack.pathtorun.model.api;

import dev.georgeblack.pathtorun.model.Region;
import dev.georgeblack.pathtorun.model.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RoutesResponse {
  private Region region;
  private List<Route> routes;
}
