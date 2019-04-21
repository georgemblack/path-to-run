package dev.georgeblack.pathtorun.repository;

import dev.georgeblack.pathtorun.model.RunnablePath;
import org.springframework.data.repository.CrudRepository;

public interface RunnablePathRepository extends CrudRepository<RunnablePath, Double> {
}
