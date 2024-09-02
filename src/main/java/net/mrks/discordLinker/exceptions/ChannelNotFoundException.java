package net.mrks.discordLinker.exceptions;

public class ChannelNotFoundException extends BotException {
    public ChannelNotFoundException() {
        super("Le channel spécifié n'existe pas.");
    }
}
