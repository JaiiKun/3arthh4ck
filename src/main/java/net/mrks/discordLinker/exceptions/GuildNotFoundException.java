package net.mrks.discordLinker.exceptions;

public class GuildNotFoundException extends BotException {
    public GuildNotFoundException() {
        super("La guilde spécifiée n'existe pas.");
    }
}