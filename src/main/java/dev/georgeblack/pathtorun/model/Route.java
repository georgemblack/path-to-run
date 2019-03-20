package dev.georgeblack.pathtorun.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Route {
    private List<Coordinate> coordinates;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < coordinates.size(); i++) {
            if (i != coordinates.size() - 1) sb.append(coordinates.get(i)).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
