package net.mrks.discordLinker.minecraft.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.mrks.discordLinker.DiscordLinker;
import net.mrks.discordLinker.bot.DiscordBot;
import net.mrks.discordLinker.bot.utils.EmbedUtil;
import net.mrks.discordLinker.minecraft.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;

public class PlayerChatListener implements Listener {
    private final DiscordBot discordBot;

    public PlayerChatListener(DiscordBot discordBot) {
        this.discordBot = discordBot;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        EmbedBuilder embed = EmbedUtil.createEmbed(player.getName(), "Message", message, new Color(0x27E320), PlayerUtil.getAvatar(player));

        try {
            discordBot.sendEmbed("", embed, discordBot.getConfig().getLogChannel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
