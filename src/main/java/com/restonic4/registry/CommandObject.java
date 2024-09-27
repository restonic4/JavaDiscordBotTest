package com.restonic4.registry;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

public abstract class CommandObject {
    public abstract SlashCommandData create(Guild guild);

    public abstract void onExecute(SlashCommandInteractionEvent event);
}
