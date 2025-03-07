package com.rooxchicken.agnilib.Tasks;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.rooxchicken.agnilib.AgniLib;
import com.rooxchicken.agnilib.Objects.Component;
import com.rooxchicken.agnilib.Objects.Image;
import com.rooxchicken.agnilib.Objects.Text;

public class PreloadImages extends Task
{
    private Player player;

    public PreloadImages(AgniLib _plugin, Player _player)
    {
        super(_plugin);
        player = _player;

        for(int i = 1; i < 531; i++)
            Image.preload("video_" + i, new File("frames/" + i + ".png"), player);
        
        tickThreshold = 1;
    }

    @Override
    public void run()
    {
        // for(int i = t; i < Math.min(531, t+10); i++)
        //     Image.preload("video_" + i, new File("frames/" + i + ".png"), player);

        // if(t+10 > 531)
            cancel = true;
    }

    @Override
    public void onCancel()
    {
        Bukkit.getLogger().info("Finished loading");
    }
}
