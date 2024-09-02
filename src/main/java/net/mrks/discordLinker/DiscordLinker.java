package net.mrks.discordLinker;

import net.mrks.discordLinker.bot.DiscordBot;
import net.mrks.discordLinker.minecraft.events.PlayerChatListener;
import org.bukkit.plugin.java.JavaPlugin;
import net.mrks.discordLinker.bot.DiscordBotConfig;

import java.util.List;

public final class DiscordLinker extends JavaPlugin {

    @Override
    public void onEnable() {
        DiscordBotConfig config = new DiscordBotConfig("TOKEN", "732648510037426228", List.of("1279576841849405593"), List.of("1279576841849405593"), "760844498019549204");
        DiscordBot bot = new DiscordBot(config);
        try {
            bot.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new PlayerChatListener(bot), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
