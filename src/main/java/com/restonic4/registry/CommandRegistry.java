package com.restonic4.registry;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRegistry {
    private static Map<String, CommandObject> registry = new HashMap<>();

    public static void register(Guild guild, CommandObject commandObject) {
        SlashCommandData command = commandObject.create(guild);
        registry.put(command.getName(), commandObject);

        System.out.println("Command " + command.getName() + " registered!");
    }

    public static void executeCommand(long commandId, SlashCommandInteractionEvent event) {
        CommandObject commandObject = registry.get(commandId);

        if (commandObject != null) {
            commandObject.onExecute(event);
        }
    }
}
