package com.panett.simone.config;

import com.panett.simone.bot.AudioBridge;
import lombok.extern.log4j.Log4j2;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

@Configuration
@Log4j2
public class JDAConfig {

    public static String guildId = "439130567208861718";

    @Autowired JDA jda;

    @Bean
    public JDA jda() {
        try {
            String token = "Njg5OTQyNDg1OTAzOTk5MTA4.XnKVzg.mEgL5pZwlE8fSVccJSLqdjm4AD0";
            JDA jda = new JDABuilder(token).build().awaitReady();

            Guild guild = jda.getGuildById(guildId);
            AudioManager audioManager = guild.getAudioManager();
            VoiceChannel voiceChannel = guild.getVoiceChannelById("683695502751170609");
            audioManager.setReceivingHandler(new AudioBridge(jda));
            audioManager.openAudioConnection(voiceChannel);

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
}