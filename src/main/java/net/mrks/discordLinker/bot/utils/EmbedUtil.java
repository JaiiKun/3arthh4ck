package net.mrks.discordLinker.bot.utils;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class EmbedUtil {

    public static EmbedBuilder createEmbed(String title, String author, String description, Color color, String imageUrl) {
        return new EmbedBuilder().setTitle(title).setDescription(description).setAuthor(author).setColor(color).setThumbnail(imageUrl);
    }

}
