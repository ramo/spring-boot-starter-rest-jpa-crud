package com.ramo.sample;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Performing CRUD operations on Sample Object
 */
public interface SampleRepository extends CrudRepository<Sample, Integer> {
}
