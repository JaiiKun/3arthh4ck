package net.mrks.discordLinker.exceptions;

public class NoGuildException extends BotException {
    public NoGuildException() {
        super("Aucun serveur Discord n'a été séléctionné.");
    }
}
