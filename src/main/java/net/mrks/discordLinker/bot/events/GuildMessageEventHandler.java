package net.mrks.discordLinker.bot.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.mrks.discordLinker.bot.DiscordBotConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.server.BroadcastMessageEvent;

public class GuildMessageEventHandler extends ListenerAdapter {
    private final DiscordBotConfig config;

    public GuildMessageEventHandler(DiscordBotConfig config) {
        this.config = config;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String channelId = event.getChannel().getId();
        if ((config.getReadableChannels().contains(channelId) || channelId.equals(config.getLogChannel())) && !event.getAuthor().getId().equals("1279422668772479016")) {
            Bukkit.broadcastMessage("§8[§9Discord§8] §7" + event.getAuthor().getName() + "§8: §f" + event.getMessage().getContentRaw());
        }
    }
}
