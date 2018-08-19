package com.ramo.sample;

import java.util.List;

/**
 * Sample CRUD service
 */
public interface SampleService {

    /**
     * Add a sample
     * @param sample Sample object to add
     */
    void addSample(Sample sample);

    /**
     * Get sample object by id
     * @param id Sample object's id
     * @return Sample object indicated by the given id
     */
    Sample getSampleById(Integer id);

    /**
     * Get All sample objects
     * @return List of Sample objects
     */
    List<Sample> getAllSamples();

    /**
     * Update a sample object
     * @param sample Sample object to update
     */
    void updateSample(Sample sample);

    /**
     * Delete a sample from db
     * @param id Id of sample object to be deleted
     */
    void deleteSampleById(Integer id);
}
