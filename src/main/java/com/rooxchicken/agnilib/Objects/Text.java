package com.rooxchicken.agnilib.Objects;

import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.rooxchicken.agnilib.AgniLib;
import com.rooxchicken.agnilib.Data.Parser;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Text extends Component
{
    private static final short textID = 3;

    public String text;
    public Color color;

    public Text(String _id, String _text, Color _color, boolean _positionType, double _posX, double _posY, double _scaleX, double _scaleY)
    {
        super(_id, _positionType, _posX, _posY, _scaleX, _scaleY);

        text = _text;
        color = _color;
    }

    public void setText(String _text, Player ... _players)
    {
        if(_text.equals(text))
            return;

        text = _text;
        checkAndSend(_players);
    }

    public void setColor(Color _color, Player ... _players)
    {
        if(_color.equals(color))
            return;

        color = _color;
        checkAndSend(_players);
    }

    @Override
    protected void _sendData(Player ... _players)
    {
        ByteBuf _buf = Unpooled.buffer();
        _buf.writeShort(textID);

        Parser.writeString(id, _buf);
        Parser.writeString(text, _buf);
        _buf.writeInt(color.asARGB());

        for(Player _player : _players)
            AgniLib.sendData(_player, _buf.array());

        super._sendData(_players);
    }

    @Override
    protected void _destroy(Player ... _players)
    {
        super._destroy(_players);
    }
}
