package com.restonic4;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.utils.JDALogger;

//invite perms id = 1759218067566535
//invite = https://discord.com/oauth2/authorize?client_id=1287694211906539520

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");

        System.out.println(token);

        //JDALogger.setFallbackLoggerEnabled(false)

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new Listener())
                .build();

        Guild guild = jda.getGuildById("1287697784572743790");

        guild.upsertCommand("ping", "Idk");

        //jda.awaitReady();
    }
}