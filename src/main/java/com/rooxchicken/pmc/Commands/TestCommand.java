package com.rooxchicken.pmc.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rooxchicken.pmc.PMC;
import com.rooxchicken.pmc.Tasks.TestTask;

public class TestCommand implements CommandExecutor
{
    private PMC plugin;

    public TestCommand(PMC _plugin)
    {
        plugin = _plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        PMC.tasks.add(new TestTask(plugin));
        // if(sender.isOp())
        // {
        //     Player player = Bukkit.getPlayer(sender.getName());
        // }

        return true;
    }

}
