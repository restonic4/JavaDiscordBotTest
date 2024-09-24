package com.restonic4.registry;

import com.restonic4.commands.PingCommand;
import com.restonic4.commands.TrashCommand;
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

        for (int i = 0; i < commandList.size(); i++) {
            Command command = commandList.get(i);

            if (command.getName().equals(name) && !registry.containsKey(command.getIdLong())) {
                System.out.println("Registering already created command: " + name);

                registry.put(command.getIdLong(), commandObject);
                commandList.remove(i);
                return;
            }
        }

        // Not found

        System.out.println("Registering new command: " + name);

        CommandCreateAction commandCreateAction = guild.upsertCommand(commandObject.getName(), commandObject.getDescription());
        commandObject.setCommandCreateAction(commandCreateAction);
        commandCreateAction.queue(command -> {
            registry.put(command.getIdLong(), commandObject);
        });
    }

    public static void registerCommands(Guild guild) {
        guild.retrieveCommands().queue(commandList -> {
            getOrCreateCommand(guild, commandList, new PingCommand());
            //getOrCreateCommand(guild, commandList, new TrashCommand());

            removeTrash(guild, commandList);
        }, error -> {
            System.out.println("Error retrieving commands: " + error.getMessage());
        });
    }

    public static void removeTrash(Guild guild, List<Command> commandList) {
        for (Command command : commandList) {
            System.out.println("Removing command: " + command);

            guild.deleteCommandById(command.getIdLong()).queue();
        }
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
