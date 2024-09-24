package com.restonic4.registry;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

public abstract class CommandObject {
    private final String name, description;
    private CommandCreateAction commandCreateAction;

    public CommandObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void onPopulate();

    public abstract void onExecute(SlashCommandInteractionEvent event);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandCreateAction getCommandCreateAction() {
        return commandCreateAction;
    }

    public void setCommandCreateAction(CommandCreateAction commandCreateAction) {
        this.commandCreateAction = commandCreateAction;
    }

    public boolean needsToBeRegistered() {
        return commandCreateAction != null;
    }

    @Override
    public String toString() {
        return name;
    }
}
