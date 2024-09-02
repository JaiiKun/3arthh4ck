package net.mrks.discordLinker.exceptions;

public class InvalidTokenException extends BotException {
    public InvalidTokenException() {
        super("Le token du bot Discord est invalide.");
    }
}
