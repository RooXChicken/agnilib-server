package com.rooxchicken.pmc.Tasks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.rooxchicken.pmc.PMC;
import com.rooxchicken.pmc.Objects.Component;
import com.rooxchicken.pmc.Objects.Text;

public class TestTask extends Task
{
    private Text text;
    private Text sillyText;
    private Text fateText;

    public TestTask(PMC _plugin)
    {
        super(_plugin);

        // Component _cmp = new Component("hi", 100, 100, 2.4, 2.7);
        text = new Text("testtext", "This is a test!", Color.WHITE, 100, 100, 2, 2);
        // fateText = new Text("fate", "fate is super cool", Color.BLUE, 200, 100, 0.8, 4);
        // sillyText = new Text("hey", "hey ;)", Color.RED, 100, 300, 1, 2);
        // text.setColor(Color.WHITE, null);
        // text.sendOnUpdate = true;

        // text.players = new ArrayList<Player>();
        for(Player _player : Bukkit.getOnlinePlayers())
            text.players.add(_player);

        text.sendData(null);

        // sillyText.players = text.players;
        // fateText.players = text.players;
        // sillyText.sendData(null);
        // fateText.sendData(null);

        tickThreshold = 1;
    }

    @Override
    public void run()
    {
        ++t;
        text.setText("Hello! " + t, null);
        // sillyText.setPosition((Math.sin(Math.toRadians(t*12))+1) * 300, 200, null);
        // fateText.setScale((Math.sin(Math.toRadians(t*12 + 180))+1) + 1, (Math.cos(Math.toRadians(t*8 + 90))+1) + 2, null);
    }

    @Override
    public void onCancel()
    {
        // text.destroy(null);
    }
}
