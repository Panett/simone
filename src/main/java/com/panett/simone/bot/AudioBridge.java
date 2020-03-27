package com.panett.simone.bot;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.UserAudio;
import net.dv8tion.jda.api.entities.Guild;
import org.springframework.util.StopWatch;

import java.util.concurrent.ConcurrentLinkedQueue;

public class AudioBridge implements AudioReceiveHandler {

    private Simone simone;
    private Guild guild;
    private String panettId = "169531615528222720";

    double volume = 1.0;
    ConcurrentLinkedQueue<byte[]> bridgeQueue = new ConcurrentLinkedQueue<>();

    public AudioBridge(Guild guild) {
        this.guild = guild;
        this.simone = new Simone();
    }

    @Override
    public boolean canReceiveCombined() {
        return true;
    }

    @Override
    public boolean canReceiveUser() {
        return true;
    }

    @Override
    public void handleCombinedAudio(CombinedAudio combinedAudio) {
        bridgeQueue.add(combinedAudio.getAudioData(volume));
    }


    //TODO: vedere guava RateLimiter
    @Override
    public void handleUserAudio(UserAudio userAudio) {
        if(userAudio.getUser().getId().equals(panettId)) {
            System.out.println("stai a parla");

            if(!simone.isTalking()) {
                simone.setTalking(true);
                //simone.getStopwatch().start();
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
            } else {
                long lastTaskTimeMillis = simone.getStopwatch().getLastTaskTimeMillis();
                System.out.println(lastTaskTimeMillis);
            }

        } else {
            simone.setTalking(false);
        }
    }

}