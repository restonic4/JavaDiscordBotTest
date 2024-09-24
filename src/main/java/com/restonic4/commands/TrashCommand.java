package com.restonic4.commands;

import com.restonic4.registry.CommandObject;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

public class TrashCommand extends CommandObject {
    public TrashCommand() {
        super(
                "trash",
                "does nothing"
        );
    }

    @Override
    public void onPopulate() {
        if (needsToBeRegistered()) {
            CommandCreateAction commandCreateAction = getCommandCreateAction();
        }
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        event.getChannel().sendMessage("did nothing");
    }
}
