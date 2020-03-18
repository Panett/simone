package com.panett.simone.bot;

import com.panett.simone.config.JDAConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.UserAudio;

import java.util.concurrent.ConcurrentLinkedQueue;

public class AudioBridge implements AudioReceiveHandler {

    private JDA jda;
    private String panettId = "169531615528222720";

    double volume = 1.0;
    ConcurrentLinkedQueue<byte[]> bridgeQueue = new ConcurrentLinkedQueue<>();

    public AudioBridge(JDA jda) {
        this.jda = jda;
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
            jda.getGuildById(JDAConfig.guildId).getTextChannels().get(0).sendMessage("sei baned").submit();
        }
    }

}