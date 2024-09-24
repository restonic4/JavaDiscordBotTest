package com.restonic4.commands;

import com.restonic4.registry.CommandObject;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

public class PingCommand extends CommandObject {
    public PingCommand() {
        super(
                "ping",
                "Gives you pong"
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
        event.getChannel().sendMessage("Pong");
    }
}
