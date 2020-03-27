package com.panett.simone.bot;

import com.google.common.base.Stopwatch;

import java.util.Observable;

public class Simone extends Observable {

    private boolean isTalking;
    private Stopwatch talkingStopwatch;
    private Stopwatch notTalkingStopwatch;

    private long talkingSecs;

    public Simone() {
        this.talkingStopwatch = Stopwatch.createUnstarted();
        this.notTalkingStopwatch = Stopwatch.createUnstarted();
    }

    public boolean isTalking() {
        return isTalking;
    }

    public void setTalking(boolean talking) {
        isTalking = talking;
    }

    public Stopwatch getTalkingStopwatch() {
        return talkingStopwatch;
    }

    public void setTalkingStopwatch(Stopwatch talkingStopwatch) {
        this.talkingStopwatch = talkingStopwatch;
    }

    public Stopwatch getNotTalkingStopwatch() {
        return notTalkingStopwatch;
    }

    public void setNotTalkingStopwatch(Stopwatch notTalkingStopwatch) {
        this.notTalkingStopwatch = notTalkingStopwatch;
    }

    public long getTalkingSecs() {
        return talkingSecs;
    }

    public void setTalkingSecs(long talkingSecs) {
        this.talkingSecs = talkingSecs;
        setChanged();
        notifyObservers();
    }

}
