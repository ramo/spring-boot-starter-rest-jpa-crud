package com.ramo.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    @Override
    public void addSample(Sample sample) {
        sampleRepository.save(sample);
    }

    @Override
    public Sample getSampleById(Integer id) {
        return sampleRepository.findById(id).get();
    }

    @Override
    public List<Sample> getAllSamples() {
        List<Sample> sampleList = new LinkedList<>();
        sampleRepository.findAll().forEach(i -> sampleList.add(i));
        return new ArrayList<>(sampleList);
    }

    @Override
    public void updateSample(Sample sample) {
        sampleRepository.save(sample);
    }

    @Override
    public void deleteSampleById(Integer id) {
        sampleRepository.deleteById(id);
    }
}
