package com.gustavo.texoit.razziesapi.application;

import com.gustavo.texoit.razziesapi.domain.ProducerRazzies;
import com.gustavo.texoit.razziesapi.domain.ProducerService;
import com.gustavo.texoit.razziesapi.domain.worstmovie.MovieAwardInterval;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/producers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RazziesProducerController {

    private final ProducerService producerService;

    @GetMapping("/razzies")
    @ApiResponse(description = "Intervalo de Prêmios")
    public ResponseEntity<RazzieAwardsIntervalResponse> findAwards() {
        RazzieAwardsIntervalResponse response = new RazzieAwardsIntervalResponse();
        MovieAwardInterval gaps = producerService.getIntervalBetweenAwards();

        responseWriter(response.getMin(), gaps.getMin());
        responseWriter(response.getMax(), gaps.getMax());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void responseWriter (List<RazziesProducerResponse> response, List<ProducerRazzies> producerRazzies) {
        producerRazzies.forEach(p -> {
            RazziesProducerResponse producerResponse = new RazziesProducerResponse();
            producerResponse.setProducer(p.getProducer());
            producerResponse.setFollowingWin(p.getCurrentAward());
            producerResponse.setPreviousWin(p.getPreviousAward());
            producerResponse.setInterval(p.getTimeGap());
            response.add(producerResponse);
        });
    }

}
