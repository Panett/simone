package com.panett.simone.bot;

import java.util.Observable;
import java.util.Observer;

public class SimoneObserver implements Observer {

    private Simone simone;

    public SimoneObserver(Simone simone) {
        this.simone = simone;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == simone) {
            long talkingSecs = ((Simone) o).getTalkingSecs();
            System.out.println("Stai parlando da " + talkingSecs + " secondi");
            //controllare da quanto non parlo
        }
    }
}
