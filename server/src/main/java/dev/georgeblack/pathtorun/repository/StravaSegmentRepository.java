package dev.georgeblack.pathtorun.repository;

import dev.georgeblack.pathtorun.model.strava.StravaSegment;
import org.springframework.data.repository.CrudRepository;

public interface StravaSegmentRepository extends CrudRepository<StravaSegment, Integer> {}
