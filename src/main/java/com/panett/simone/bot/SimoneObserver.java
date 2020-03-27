package com.panett.simone.bot;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class SimoneObserver implements Observer {

    private Simone simone;
    Timer timer;
    boolean timerOn = false;

    public SimoneObserver(Simone simone) {
        this.simone = simone;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == simone) {

            if (timerOn) {
                timer.cancel();
                timerOn = false;
                // if(simone.getTalkingSecs() > 5) playMusic();
            } else {
                timer = new Timer();
                TimerTask resetSimoneTask = new TimerTask() {
                    @Override
                    public void run() {
                        simone.setTalking(false);
                        simone.getTalkingStopwatch().reset();
                        simone.setTalkingSecs(0);
                        // stopMusic();
                    }
                };
                timer.schedule(resetSimoneTask,3*1000);
                timerOn = true;
            }
        }
    }
}
