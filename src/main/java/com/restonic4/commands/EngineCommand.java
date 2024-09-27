package com.restonic4.commands;

import com.restonic4.registry.CommandObject;
import com.restonic4.registry.CommandRegistry;
import com.restonic4.util.GitHubHelper;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;

public class EngineCommand extends CommandObject {
    @Override
    public SlashCommandData create(Guild guild) {
        SlashCommandData command = Commands.slash("engine", "Do something related with the engine")
                .addOption(OptionType.STRING, "action", "The action to do", true)
                .addOption(OptionType.STRING, "argument", "The action's argument", true);

        return command;
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        OptionMapping actionOption = event.getOption("action");
        OptionMapping argOption = event.getOption("argument");

        if (actionOption == null) {
            event.reply("Action missing");
        }

        if (argOption == null) {
            event.reply("Arugment missing");
        }

        if (actionOption.getAsString().equals("info")) {
            if (argOption.getAsString().equals("version")) {
                event.reply("Latest version: " + getLastEngineVersion()).queue();
            }
        }
        else {
            event.reply("Invalid action");
        }
    }

    private String getLastEngineVersion() {
        return GitHubHelper.getLatestRelease("restonic4", "CitadelEngine");
    }
}
