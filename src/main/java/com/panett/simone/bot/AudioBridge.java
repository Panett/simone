package com.panett.simone.bot;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.UserAudio;
import net.dv8tion.jda.api.entities.Guild;
import com.google.common.base.Stopwatch;

import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

public class AudioBridge implements AudioReceiveHandler {

    private Simone simone;
    private Guild guild;
    private String panettId = "169531615528222720";

    double volume = 1.0;
    ConcurrentLinkedQueue<byte[]> bridgeQueue = new ConcurrentLinkedQueue<>();

    public AudioBridge(Guild guild) {
        this.guild = guild;
        this.simone = new Simone();
        simone.addObserver(new SimoneObserver(simone));
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
            Stopwatch talkingStopwatch = simone.getTalkingStopwatch();
            if(!talkingStopwatch.isRunning()) talkingStopwatch.start();
            else simone.setTalkingSecs(talkingStopwatch.elapsed(SECONDS));
        }
    }

}