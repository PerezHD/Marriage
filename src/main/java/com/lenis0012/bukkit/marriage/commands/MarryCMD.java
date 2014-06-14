package com.lenis0012.bukkit.marriage.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lenis0012.bukkit.marriage.Marriage;

public class MarryCMD implements CommandExecutor {

    private Map<String, CommandBase> commands = new HashMap<String, CommandBase>();
    private MarryCommand marryCommand;

    public MarryCMD() {
        Marriage plugin = Marriage.instance;
        this.marryCommand = new MarryCommand();
        commands.put("accept", new AcceptCommand());
        commands.put("chat", new ChatCommand());
        commands.put("divorce", new DivorceCommand());
        commands.put("info", new InfoCommand());
        commands.put("reload", new ReloadCommand());
        commands.put("seen", new SeenCommand());
        if (plugin.getConfig().getBoolean("settings.enable-chatspy")) {
            commands.put("chatspy", new ChatspyCommand());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandBase command;
        if (args.length == 0) {
            command = commands.get("info");
        } else if (commands.containsKey(args[0])) {
            command = commands.get(args[0]);
        } else {
            command = this.marryCommand;
        }

        command.execute(sender, args);
        return true;
    }
}
