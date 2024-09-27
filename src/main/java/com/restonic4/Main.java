package com.restonic4;

import com.restonic4.commands.EngineCommand;
import com.restonic4.commands.PingCommand;
import com.restonic4.registry.CommandRegistry;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.List;

//invite perms id = 1759218067566535
//invite = https://discord.com/oauth2/authorize?client_id=1287694211906539520

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");

        System.out.println(token);

        setUpBot(token);
    }

    public static void setUpBot(String token) {
        try {
            //JDALogger.setFallbackLoggerEnabled(false)

            JDA jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
                    .addEventListeners(new Listener())
                    .build();

            // Wait for JDA to log in with discord
            jda.awaitReady();

            List<Guild> guilds = jda.getGuilds();
            for (Guild guild : guilds) {
                System.out.println("Guild: " + guild);

                CommandRegistry.register(guild, new PingCommand());
                CommandRegistry.register(guild, new EngineCommand());
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}