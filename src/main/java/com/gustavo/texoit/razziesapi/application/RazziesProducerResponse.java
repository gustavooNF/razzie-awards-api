package com.gustavo.texoit.razziesapi.application;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RazziesProducerResponse {

    private String producer;
    private Integer previousWin;
    private Integer followingWin;
    private Integer interval;
}
