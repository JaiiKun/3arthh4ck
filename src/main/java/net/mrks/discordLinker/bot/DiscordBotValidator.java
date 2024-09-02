package net.mrks.discordLinker.bot;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.JDABuilder;
import net.mrks.discordLinker.exceptions.*;

import javax.security.auth.login.LoginException;
import java.util.List;


public class DiscordBotValidator {
    private final DiscordBotConfig config;
    private JDABuilder builder;

    public DiscordBotValidator(DiscordBotConfig config) {
        this.config = config;
    }

    public void validate() throws InvalidTokenException, GuildNotFoundException, BotNotInGuildException, ChannelNotFoundException, ChannelNotInGuildException, NoGuildException {
        validateToken(config.getToken());
        validateGuild(config.getGuildId());
        List<String> readableChannels = config.getReadableChannels();
        List<String> writableChannels = config.getWritableChannels();
        validateChannels(config.getGuildId(), readableChannels, true);
        validateChannels(config.getGuildId(), writableChannels, true);
    }

    public void validateToken(String token) throws InvalidTokenException {
        try {
            builder = JDABuilder.createDefault(token);
            builder.build().awaitReady();
        } catch (InterruptedException e) {
            throw new InvalidTokenException();
        }
    }

    public void validateGuild(String guildId) throws GuildNotFoundException, BotNotInGuildException {
        try {
            var jda = builder.build().awaitReady();
            var guild = jda.getGuildById(guildId);

            if (guild == null) {
                throw new GuildNotFoundException();
            }

            // Vérifier si le bot est membre de la guilde
            if (!guild.getSelfMember().isOwner() && !guild.getSelfMember().hasPermission(net.dv8tion.jda.api.Permission.ADMINISTRATOR)) {
                throw new BotNotInGuildException();
            }
        } catch (InterruptedException e) {
            throw new GuildNotFoundException();
        }
    }

    public void validateChannels(String guildId, List<String> channelIds, boolean checkExistence) throws ChannelNotFoundException, ChannelNotInGuildException, NoGuildException {
        try {
            var jda = builder.build().awaitReady();
            var guild = jda.getGuildById(guildId);

            if (guild == null) {
                throw new NoGuildException();
            }

            for (String channelId : channelIds) {
                TextChannel channel = guild.getTextChannelById(channelId);

                if (checkExistence && channel == null) {
                    throw new ChannelNotFoundException();
                } else if (!checkExistence && channel != null && !guild.getTextChannels().contains(channel)) {
                    throw new ChannelNotInGuildException();
                }
            }
        } catch (InterruptedException e) {
            throw new ChannelNotFoundException();
        }
    }

}
