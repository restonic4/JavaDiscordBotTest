package com.restonic4.commands;

import com.restonic4.registry.CommandObject;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

public class PingCommand extends CommandObject {
    @Override
    public SlashCommandData create(Guild guild) {
        SlashCommandData command = Commands.slash("ping", "Gives you pong!");

        guild.updateCommands().addCommands(command).queue();

        return command;
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        event.reply("Pong").queue();
    }
}
