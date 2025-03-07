package com.rooxchicken.agnilib.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rooxchicken.agnilib.AgniLib;
import com.rooxchicken.agnilib.Objects.Image;

public class TestCommand implements CommandExecutor
{
    private AgniLib plugin;

    public TestCommand(AgniLib _plugin)
    {
        plugin = _plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        // Player _player = Bukkit.getPlayer(sender.getName());
        // AgniLib.tasks.add(new TestTask(plugin));
        // Image.preload("test_img", plugin.getResource("test.png"), _player);
        // if(sender.isOp())
        // {
            // }

        return true;
    }

}
