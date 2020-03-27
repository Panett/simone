package com.panett.simone.config;

import com.panett.simone.bot.AudioBridge;
import lombok.extern.log4j.Log4j2;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

@Configuration
@Log4j2
public class JDAConfig {

    @Value("${guild.id}")
    String guildId;

    @Value("${user.id}")
    String userId;

    @Autowired
    JDA jda;

    @Bean
    public JDA jda() {
        try {
            String token = "Njg5OTQyNDg1OTAzOTk5MTA4.XnKVzg.mEgL5pZwlE8fSVccJSLqdjm4AD0";
            JDA jda = new JDABuilder(token).build().awaitReady();
            Guild guild = jda.getGuildById(guildId);
            User user = jda.getUserById(userId);
            Member member = guild.getMember(user);
            for(VoiceChannel voiceChannel : guild.getVoiceChannels()) {
                if(voiceChannel.getMembers().contains(member)) {
                    AudioManager audioManager = guild.getAudioManager();
                    audioManager.openAudioConnection(voiceChannel);
                    audioManager.setReceivingHandler(new AudioBridge(guild, user));
                }
            }
            return jda;
        } catch (LoginException e) {
            log.error("Discord login error:", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public TextChannel textChannel() {
        return jda.getGuildById(guildId).getTextChannels().get(0);
    }

    @Bean
    public Guild guild() {
        return jda.getGuildById(guildId);
    }
}