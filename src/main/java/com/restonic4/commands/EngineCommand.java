package com.restonic4.commands;

import com.restonic4.registry.CommandObject;
import com.restonic4.registry.CommandRegistry;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

public class EngineCommand extends CommandObject {
    public EngineCommand() {
        super(
                "engine",
                "Gives you info about the engine"
        );
    }

    @Override
    public void onPopulate() {
        if (needsToBeRegistered()) {
            getCommandCreateAction()
                .addOption(OptionType.STRING,"action", "the action", true)
                .addOption(OptionType.STRING, "type", "the type", true)
            .queue();
        }
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        OptionMapping actionOption = event.getOption("action");
        OptionMapping typeOption = event.getOption("type");

        if (actionOption != null && actionOption.getAsString().equals("info")) {
            if (typeOption != null && typeOption.getAsString().equals("version")) {
                event.reply("Latest version: " + getLastEngineVersion()).queue();
            }
        }

        event.reply("Pong").queue();
    }

    private String getLastEngineVersion() {
        return "1.0.0";
    }
}
