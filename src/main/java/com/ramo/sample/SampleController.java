package com.ramo.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @PostMapping
    public ResponseEntity<Void> createSample(@RequestBody Sample sample, UriComponentsBuilder builder) {
        sampleService.addSample(sample);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/sample/{id}").buildAndExpand(sample.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sample> getSampleById(@PathVariable("id") Integer id) {
        Sample sample = sampleService.getSampleById(id);
        return new ResponseEntity<>(sample, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Sample>> getAllSamples() {
        List<Sample> samples = sampleService.getAllSamples();
        return new ResponseEntity<>(samples, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateSample(@RequestBody Sample sample) {
        sampleService.updateSample(sample);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSampleById(@PathVariable("id") Integer id) {
        sampleService.deleteSampleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
