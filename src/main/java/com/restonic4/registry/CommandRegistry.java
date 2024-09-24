package com.restonic4.registry;

import com.restonic4.commands.PingCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRegistry {
    private static Map<Long, CommandObject> registry = new HashMap<>();

    private static void getOrCreateCommand(Guild guild, List<Command> commandList, CommandObject commandObject) {
        String name = commandObject.getName();

        for (Command command : commandList) {
            if (command.getName().equals(name)) {
                registry.put(command.getIdLong(), commandObject);
                return;
            }
        }

        // Not found

        CommandCreateAction commandCreateAction = guild.upsertCommand(commandObject.getName(), commandObject.getDescription());
        commandObject.setCommandCreateAction(commandCreateAction);
        commandCreateAction.queue(command -> {
            registry.put(command.getIdLong(), commandObject);
        });
    }

    public static void registerCommands(Guild guild) {
        guild.retrieveCommands().queue(commandList -> {
            getOrCreateCommand(guild, commandList, new PingCommand());
        }, error -> {
            System.out.println("Error retrieving commands: " + error.getMessage());
        });
    }

    public static void populate() {
        for (CommandObject commandObject : registry.values()) {
            System.out.println(commandObject);
            commandObject.onPopulate();
        }
    }

    public static void executeCommand(long commandId, SlashCommandInteractionEvent event) {
        CommandObject commandObject = registry.get(commandId);

        if (commandObject != null) {
            commandObject.onExecute(event);
        }
    }
}
