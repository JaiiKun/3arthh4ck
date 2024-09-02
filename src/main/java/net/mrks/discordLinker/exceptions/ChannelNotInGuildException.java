package net.mrks.discordLinker.exceptions;

public class ChannelNotInGuildException extends BotException {
    public ChannelNotInGuildException() {
        super("Le channel spécifié n'existe pas dans la guilde spécifiée.");
    }
}
