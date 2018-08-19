package com.ramo.sample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SampleController.class, secure = false)
public class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleService sampleService;

    private Logger logger = LoggerFactory.getLogger(SampleControllerTest.class);

    private Sample sample = new Sample(1, "test");
    private String expected = "{\"id\":1,\"value\":\"test\"}";

    @Test
    public void getSampleById() throws Exception {
        Mockito.when(sampleService.getSampleById(Mockito.anyInt())).thenReturn(sample);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sample/1").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        logger.info("Response: {} {}.", result.getResponse().getStatus(), result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getAllSamples() throws Exception {
        Sample sample = new Sample();
        sample.setId(1);
        sample.setValue("test");
        List<Sample> sampleList = Collections.singletonList(sample);
        Mockito.when(sampleService.getAllSamples()).thenReturn(sampleList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sample").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        logger.info("Response: {} {}.", result.getResponse().getStatus(), result.getResponse().getContentAsString());
        String expected = "[{\"id\":1,\"value\":\"test\"}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }


    @Test
    public void createSample() throws Exception {
        Mockito.doNothing().when(sampleService).addSample(sample);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sample")
                .accept(MediaType.APPLICATION_JSON)
                .content(expected)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        logger.info("Response: {} {}.", result.getResponse().getStatus(), result.getResponse().getContentAsString());

        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Assert.assertEquals("http://localhost/sample/1",
                response.getHeader(HttpHeaders.LOCATION));
    }

    @Test
    public void updateSample() throws Exception {
        Mockito.doNothing().when(sampleService).updateSample(sample);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/sample")
                .accept(MediaType.APPLICATION_JSON)
                .content(expected)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        logger.info("Response: {} {}.", result.getResponse().getStatus(), result.getResponse().getContentAsString());

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    public void deleteSampleById() throws Exception {
        Mockito.doNothing().when(sampleService).deleteSampleById(Mockito.anyInt());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/sample/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        logger.info("Response: {} {}.", result.getResponse().getStatus(), result.getResponse().getContentAsString());

        Assert.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
