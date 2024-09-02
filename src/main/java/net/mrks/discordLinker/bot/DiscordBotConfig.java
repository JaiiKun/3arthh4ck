package net.mrks.discordLinker.bot;

import net.mrks.discordLinker.exceptions.*;

import java.util.List;

public class DiscordBotConfig {
    private String token;
    private String guildId;
    private List<String> readableChannels;
    private List<String> writableChannels;
    private String logChannel;
    private final DiscordBotValidator validator;

    public DiscordBotConfig(String token, String guildId, List<String> readableChannels, List<String> writableChannels, String logChannel) {
        this.token = token;
        this.guildId = guildId;
        this.readableChannels = readableChannels;
        this.writableChannels = writableChannels;
        this.logChannel = logChannel;
        this.validator = new DiscordBotValidator(this);
        validateConfig();
    }

    private void validateConfig() {
        try {
            validator.validate();
        } catch (Exception e) {
            System.err.println("Erreur lors de la validation de la configuration: " + e.getMessage());
            e.printStackTrace();
            this.token = null;
            this.guildId = null;
            this.readableChannels = null;
            this.writableChannels = null;
            this.logChannel = null;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        try {
            validator.validateToken(token);
        } catch (InvalidTokenException e) {
            System.err.println("Token invalide: " + e.getMessage());
            e.printStackTrace();
            this.token = null;
        }
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
        try {
            validator.validateToken(token);
            validator.validateGuild(guildId);
        } catch (InvalidTokenException e) {
            System.err.println("Token invalide lors de la validation de la guilde: " + e.getMessage());
            e.printStackTrace();
            this.token = null;
        } catch (GuildNotFoundException e) {
            System.err.println("Guilde non trouvée: " + e.getMessage());
            e.printStackTrace();
            this.guildId = null;
        } catch (BotNotInGuildException e) {
            System.err.println("Le bot n'est pas dans la guilde: " + e.getMessage());
            e.printStackTrace();
            this.guildId = null;
        }
    }

    public List<String> getReadableChannels() {
        return readableChannels;
    }

    public void addReadableChannel(String readableChannel) {
        this.readableChannels.add(readableChannel);
        try {
            validator.validateToken(token);
            validator.validateChannels(guildId, this.readableChannels, true);
        } catch (InvalidTokenException e) {
            System.err.println("Token invalide lors de l'ajout d'un canal lisible: " + e.getMessage());
            e.printStackTrace();
            this.readableChannels.remove(readableChannel);
        } catch (ChannelNotFoundException e) {
            System.err.println("Canal non trouvé lors de l'ajout d'un canal lisible: " + e.getMessage());
            e.printStackTrace();
            this.readableChannels.remove(readableChannel);
        } catch (ChannelNotInGuildException e) {
            System.err.println("Canal non présent dans la guilde lors de l'ajout d'un canal lisible: " + e.getMessage());
            e.printStackTrace();
            this.readableChannels.remove(readableChannel);
        } catch (NoGuildException e) {
            System.err.println("La guilde n'existe pas: " + e.getMessage());
            e.printStackTrace();
            this.logChannel = null;
        }
    }

    public List<String> getWritableChannels() {
        return writableChannels;
    }

    public void addWritableChannel(String writableChannel) {
        this.writableChannels.add(writableChannel);
        try {
            validator.validateToken(token);
            validator.validateChannels(guildId, this.writableChannels, true);
        } catch (InvalidTokenException e) {
            System.err.println("Token invalide lors de l'ajout d'un canal écrivable: " + e.getMessage());
            e.printStackTrace();
            this.writableChannels.remove(writableChannel);
        } catch (ChannelNotFoundException e) {
            System.err.println("Canal non trouvé lors de l'ajout d'un canal écrivable: " + e.getMessage());
            e.printStackTrace();
            this.writableChannels.remove(writableChannel);
        } catch (ChannelNotInGuildException e) {
            System.err.println("Canal non présent dans la guilde lors de l'ajout d'un canal écrivable: " + e.getMessage());
            e.printStackTrace();
            this.writableChannels.remove(writableChannel);
        } catch (NoGuildException e) {
            System.err.println("La guilde n'existe pas: " + e.getMessage());
            e.printStackTrace();
            this.logChannel = null;
        }
    }

    public String getLogChannel() {
        return logChannel;
    }

    public void setLogChannel(String logChannel) {
        this.logChannel = logChannel;
        try {
            validator.validateToken(token);
            validator.validateChannels(guildId, List.of(logChannel), true);
        } catch (InvalidTokenException e) {
            System.err.println("Token invalide lors de la définition du canal de log: " + e.getMessage());
            e.printStackTrace();
            this.logChannel = null;
        } catch (ChannelNotFoundException e) {
            System.err.println("Canal de log non trouvé: " + e.getMessage());
            e.printStackTrace();
            this.logChannel = null;
        } catch (ChannelNotInGuildException e) {
            System.err.println("Canal de log non présent dans la guilde: " + e.getMessage());
            e.printStackTrace();
            this.logChannel = null;
        }
        catch (NoGuildException e) {
            System.err.println("La guilde n'existe pas: " + e.getMessage());
            e.printStackTrace();
            this.logChannel = null;
        }
    }
}
