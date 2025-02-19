package com.rooxchicken.pmc.Objects;

import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.rooxchicken.pmc.PMC;
import com.rooxchicken.pmc.Data.Parser;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Text extends Component
{
    private static final short textID = 2;

    protected String text;
    protected Color color;

    public Text(String _id, String _text, Color _color, double _posX, double _posY, double _scaleX, double _scaleY)
    {
        super(_id, _posX, _posY, _scaleX, _scaleY);

        text = _text;
        color = _color;
    }

    public void setText(String _text, @Nullable List<Player> _players)
    {
        if(_text.equals(text))
            return;

        text = _text;
        checkAndSend(_players);
    }

    public void setColor(Color _color, @Nullable List<Player> _players)
    {
        if(_color.equals(color))
            return;

        color = _color;
        checkAndSend(_players);
    }

    @Override
    protected void _sendData(List<Player> _players)
    {
        ByteBuf _buf = Unpooled.buffer();
        _buf.writeShort(textID);

        Parser.writeString(id, _buf);
        Parser.writeString(text, _buf);
        _buf.writeInt(color.asARGB());

        for(Player _player : _players)
            PMC.sendData(_player, _buf.array());

        super._sendData(_players);
    }

    @Override
    public void destroy(@Nullable List<Player> _players)
    {
        // ByteArrayDataOutput _out = PMC.generateDataOutput(id);

        // if(_players == null)
        //     _sendData(players, TEXT_DEL, _out);
        // else
        //     _sendData(_players, TEXT_DEL, _out);
    }
}
