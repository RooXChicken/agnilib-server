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
        
        image = new Image("kita", plugin.getResource("test.png"), true, 0, 0, 1.0, 1.0);
        text = new Text("testtext", "This is a test!", Color.WHITE, 100, 100, 2, 2);

        for(Player _player : Bukkit.getOnlinePlayers())
        {
            image.players.add(_player);
            text.players.add(_player);
        }

        image.sendData(null);
        text.sendData(null);

        tickThreshold = 1;
    }

    @Override
    public void run()
    {
        text.setText("Hello! " + t, null);
        if(++t > 1000)
            cancel = true;
    }

    @Override
    public void onCancel()
    {
        text.destory(null);
        image.destory(null);
    }
}
