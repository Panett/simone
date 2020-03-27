package com.panett.simone.bot;

import com.google.common.base.Stopwatch;
import lombok.Getter;
import lombok.Setter;

import java.util.Observable;

@Getter
@Setter
public class Simone extends Observable {

    private boolean isTalking;
    private Stopwatch talkingStopwatch;
    private Stopwatch notTalkingStopwatch;

    private long talkingSecs;

    public Simone() {
        this.talkingStopwatch = Stopwatch.createUnstarted();
        this.notTalkingStopwatch = Stopwatch.createUnstarted();
    }

    public void setTalkingSecs(long talkingSecs) {
        this.talkingSecs = talkingSecs;
        setChanged();
        notifyObservers();
    }

}
