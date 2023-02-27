package com.gustavo.texoit.razziesapi.domain.worstmovie;

import com.gustavo.texoit.razziesapi.domain.ProducerRazzies;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class MovieAwardGap implements Serializable {

    private static final Long serialVersionUID = 1L;

    private final List<ProducerRazzies> min;
    private final List<ProducerRazzies> max;
}
