package com.panett.simone.bot;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.UserAudio;
import net.dv8tion.jda.api.entities.Guild;
import com.google.common.base.Stopwatch;
import net.dv8tion.jda.api.entities.User;

import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

public class AudioBridge implements AudioReceiveHandler {

    private Simone simone;
    private Guild guild;
    private User user;

    double volume = 1.0;
    ConcurrentLinkedQueue<byte[]> bridgeQueue = new ConcurrentLinkedQueue<>();

    public AudioBridge(Guild guild, User user) {
        this.guild = guild;
        this.simone = new Simone();
        this.user = user;
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

    @Override
    public void handleUserAudio(UserAudio userAudio) {
        System.out.println(simone.getTalkingSecs());
        if(userAudio.getUser().equals(user)) {
            Stopwatch talkingStopwatch = simone.getTalkingStopwatch();
            if(!talkingStopwatch.isRunning()) talkingStopwatch.start();
            else simone.setTalkingSecs(talkingStopwatch.elapsed(SECONDS));
        }
    }

}