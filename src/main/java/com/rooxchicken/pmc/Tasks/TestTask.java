package com.rooxchicken.pmc.Tasks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.rooxchicken.pmc.PMC;
import com.rooxchicken.pmc.Objects.Component;
import com.rooxchicken.pmc.Objects.Image;
import com.rooxchicken.pmc.Objects.Text;

public class TestTask extends Task
{
    private Text text;
    private Image image;

    public TestTask(PMC _plugin)
    {
        super(_plugin);
        
        // image = new Image("test", "video_0", Color.WHITE, true, 0, 0, 0.8, 0.8);
        // text = new Text("testtext", "This is a test!", Color.WHITE, 100, 100, 2, 2);

        // image.sendData(Bukkit.getOnlinePlayers().toArray(new Player[] {}));
        // text.sendData(Bukkit.getOnlinePlayers().toArray(new Player[] {}));

        tickThreshold = 1;
    }

    @Override
    public void run()
    {
        // image.name = "video_" + t;
        // image.sendData(Bukkit.getOnlinePlayers().toArray(new Player[] {}));

        // if(PMC.keybinding.isJustPressed("key.sneak"))
        //     Bukkit.getLogger().info("You just toggled your perspective! Can't detect THAT one with regular spigot!");

        if(t > 1000)
            cancel = true;
    }

    @Override
    public void onCancel()
    {
        // image.destory(Bukkit.getOnlinePlayers().toArray(new Player[] {}));
        // text.destory(Bukkit.getOnlinePlayers().toArray(new Player[] {}));
    }
}
