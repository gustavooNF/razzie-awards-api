package com.gustavo.texoit.razziesapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProducerRazzies implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String producer;
    private Integer previousAward;
    private Integer currentAward;
    private Integer timeGap;
}
