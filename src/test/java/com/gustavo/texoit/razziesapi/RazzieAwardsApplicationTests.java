package com.gustavo.texoit.razziesapi;

import com.gustavo.texoit.razziesapi.application.RazzieAwardsIntervalResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RazzieAwardsApplicationTests {

    private static final String HTTP_LOCALHOST = "http://localhost:";
    private static final String V_1_PRODUCERS = "/razzieawards-api/v1/producers";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRazziesIntervalWithAnyData() {
        ResponseEntity<RazzieAwardsIntervalResponse> responseEntity = restTemplate.getForEntity(HTTP_LOCALHOST + port + V_1_PRODUCERS + "/razzies", RazzieAwardsIntervalResponse.class);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertNotNull(responseEntity.getBody().getMin());
        Assert.assertNotNull(responseEntity.getBody().getMax());
    }

    @Test
    public void testWithMoreThanOneRazzies() {
        ResponseEntity<RazzieAwardsIntervalResponse> responseEntity = restTemplate.getForEntity(HTTP_LOCALHOST + port + V_1_PRODUCERS + "/razzies", RazzieAwardsIntervalResponse.class);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(1, responseEntity.getBody().getMin().get(0).getInterval().intValue());
    }
    @Test
    public void testBiggerIntervalRazzies() {
        ResponseEntity<RazzieAwardsIntervalResponse> responseEntity = restTemplate.getForEntity(HTTP_LOCALHOST + port + V_1_PRODUCERS + "/razzies", RazzieAwardsIntervalResponse.class);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(13, responseEntity.getBody().getMax().get(0).getInterval().intValue());
    }
}