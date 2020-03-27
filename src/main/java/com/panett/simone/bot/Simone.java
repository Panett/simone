package com.panett.simone.bot;

import lombok.Data;
import org.springframework.util.StopWatch;

@Data
public class Simone {

    private boolean isTalking;
    private StopWatch stopwatch;
}
