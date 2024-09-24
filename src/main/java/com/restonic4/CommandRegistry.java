package com.restonic4;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CommandRegistry {
    public static void getOrRegisterCommand(Guild guild, String name, String description) {
        CommandCreateAction commandCreateAction = guild.upsertCommand(name, description);

    }

    public static void registerCommands(Guild guild) {
        guild.retrieveCommands().queue(commandList -> {
            if (commandList.isEmpty()) {
                System.out.println("Couldn't find any commands!");
                return;
            }

            for (Command command : commandList) {
                System.out.println("Command found in guild: " + command.getName());
            }
        }, error -> {
            System.out.println("Error retrieving commands: " + error.getMessage());
        });
    }
}
