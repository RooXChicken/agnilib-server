package com.rooxchicken.agnilib.Data;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;

import com.rooxchicken.agnilib.AgniLib;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.VarInt;

public class Parser
{
    private static int MAX_SEND_SIZE = 13240;

    public static String readString(ByteBuf _buf)
	{
		return _buf.readCharSequence(_buf.readShort(), Charset.defaultCharset()).toString();
	}

    public static void writeString(String _string, ByteBuf _buf)
    {
        _buf.writeShort(_string.trim().length());
        _buf.writeCharSequence(_string, Charset.defaultCharset());
    }

    public static List<byte[]> writeLargeArray(byte[] _data, String _id, short _dataID)
    {
        List<byte[]> data = new ArrayList<byte[]>();
        for(int i = 0; i <= _data.length/MAX_SEND_SIZE; i++)
        {
            ByteBuf _buf = Unpooled.buffer();
            _buf.writeShort(_dataID);
            Parser.writeString(_id, _buf);
            
            Parser.writeString(_data.length + "", _buf);
            
            int _start = i*MAX_SEND_SIZE;
            int _size = Math.min(MAX_SEND_SIZE, _data.length - (i * MAX_SEND_SIZE));

            Parser.writeString(_start + "", _buf);;
            Parser.writeString(_size + "", _buf);

            for(int k = _start; k < _start + _size; k++)
                _buf.writeByte(_data[k]);

            data.add(_buf.array());
        }

        return data;
    }
}
