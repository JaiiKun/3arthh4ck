package net.mrks.discordLinker.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.mrks.discordLinker.bot.events.GuildMessageEventHandler;

import java.util.List;

public class DiscordBot {
    private final DiscordBotConfig config;
    private final JDABuilder builder;


    public DiscordBot(DiscordBotConfig config) {
        this.config = config;
        this.builder = JDABuilder.createDefault(config.getToken());
        this.builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        this.builder.addEventListeners(new GuildMessageEventHandler(config));
    }

    public void start() throws Exception{
        this.builder.build().awaitReady();
    }

    public void sendMessage(String message, String channelID) throws Exception {
        this.builder.build().awaitReady().getTextChannelById(channelID).sendMessage(message).queue();
    }

    public TextChannel getChannel(String channelID) throws Exception {
        return this.builder.build().awaitReady().getTextChannelById(channelID);
    }

    public Guild getGuild() throws Exception {
        return this.builder.build().awaitReady().getGuildById(this.config.getGuildId());
    }

    public void sendEmbed(String message, EmbedBuilder embed, String channelID) throws Exception {
        this.builder.build().awaitReady().getTextChannelById(channelID).sendMessage(message).setEmbeds(embed.build()).queue();
    }

    public List<TextChannel> getChannels(Guild guild) {
        return guild.getTextChannels();
    }

    public void stop() {
        this.builder.build().shutdownNow();
    }

    public DiscordBotConfig getConfig() {
        return this.config;
    }
}
