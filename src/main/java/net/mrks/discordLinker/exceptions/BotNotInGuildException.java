package net.mrks.discordLinker.exceptions;

public class BotNotInGuildException extends BotException {
    public BotNotInGuildException() {
        super("Le bot n'est pas membre de la guilde spécifiée.");
    }
}
