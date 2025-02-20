package com.rooxchicken.pmc.Objects;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.rooxchicken.pmc.PMC;
import com.rooxchicken.pmc.Data.Parser;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Image extends Component
{
    private static final short imageID = 3;
    private static final short finishID = 4;
    private static final short MAX_SEND_SIZE = 13240;

    protected boolean blend = false;
    protected byte[] data;

    public Image(String _id, InputStream _path, boolean _blend, double _posX, double _posY, double _scaleX, double _scaleY)
    {
        super(_id, _posX, _posY, _scaleX, _scaleY);
        blend = _blend;

        try
        {
            data = _path.readAllBytes();
        }
        catch(Exception e)
        {
            Bukkit.getLogger().info("Failed to open image with id: " + id + ". " + e);
        }
    }

    @Override
    protected void _sendData(List<Player> _players)
    {
        for(int i = 0; i <= data.length/MAX_SEND_SIZE; i++)
        {
            ByteBuf _buf = Unpooled.buffer();
            _buf.writeShort(imageID);
            Parser.writeString(id, _buf);
            
            Parser.writeString(data.length + "", _buf);
            
            int _start = i*MAX_SEND_SIZE;
            int _size = Math.min(MAX_SEND_SIZE, data.length - (i * MAX_SEND_SIZE));

            Parser.writeString(_start + "", _buf);;
            Parser.writeString(_size + "", _buf);

            Bukkit.getLogger().info("Sending image chunk: " + _start + " -> " + _size);
            // _buf.writeBytes(data, _start, _size);

            for(int k = _start; k < _start + _size; k++)
                _buf.writeByte(data[k]);

            for(Player _player : _players)
                PMC.sendData(_player, _buf.array());
        }

        ByteBuf _buf = Unpooled.buffer();
        _buf.writeShort(finishID);
        Parser.writeString(id, _buf);

        for(Player _player : _players)
            PMC.sendData(_player, _buf.array());

        super._sendData(_players);
    }

    @Override
    protected void _destroy(@Nullable List<Player> _players)
    {
        super._destroy(_players);
    }
}
