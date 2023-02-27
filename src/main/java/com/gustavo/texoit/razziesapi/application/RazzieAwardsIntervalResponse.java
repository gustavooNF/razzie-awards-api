package com.gustavo.texoit.razziesapi.application;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RazzieAwardsIntervalResponse {

    private List<RazziesProducerResponse> min;
    private List<RazziesProducerResponse> max;

    public List<RazziesProducerResponse> getMin() {
        if(min == null) {
            min = Lists.newArrayList();
        }
        return min;
    }

    public List<RazziesProducerResponse> getMax() {
        if(max == null) {
            max = Lists.newArrayList();
        }
        return max;
    }
}
