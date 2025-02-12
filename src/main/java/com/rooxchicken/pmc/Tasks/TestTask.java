package com.rooxchicken.pmc.Tasks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.rooxchicken.pmc.PMC;
import com.rooxchicken.pmc.Objects.Text;

public class TestTask extends Task
{
    private Text text;
    private Text sillyText;

    public TestTask(PMC _plugin)
    {
        super(_plugin);

        text = new Text("testtext", "This is a test!", Color.WHITE, 100, 100, 2, 2);
        sillyText = new Text("hey", "hey ;)", Color.RED, 100, 300, 1, 2);
        text.setColor(Color.WHITE, null);
        text.sendOnUpdate = true;

        text.players = new ArrayList<Player>();
        for(Player _player : Bukkit.getOnlinePlayers())
            text.players.add(_player);

        sillyText.players = text.players;
        sillyText.sendData(null);

        tickThreshold = 1;
    }

    @Override
    public void run()
    {
        text.setText("Hello! " + t, null);
        sillyText.setPosition((Math.sin(Math.toRadians(t*12))+1) * 300, 200, null);
    }

    @Override
    public void onCancel()
    {
        text.destroy(null);
    }
}
