package net.mrks.discordLinker.minecraft.utils;

import org.bukkit.entity.Player;

public class PlayerUtil {
    public static String getAvatar (Player player) {
        return "https://mc-heads.net/avatar/" + player.getUniqueId();
    }
}
