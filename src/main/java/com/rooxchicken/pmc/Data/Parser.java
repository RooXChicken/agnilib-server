package com.rooxchicken.pmc.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.VarInt;

public class Parser
{
    public static byte[] parseString(String _data)
    {
        // convert string to char array
        char[] _text = _data.toCharArray();

        // create a data buffer with the size of the char array, with the plus one leaving room for the size byte
        byte[] data = new byte[_text.length+2];
        
        // tell nms the size of the byte array
        data[0] = (byte)(data.length-1);
        data[1] = (byte)_text.length;

        // fill in the data
        for(int i = 2; i < _text.length+2; i++)
            data[i] = (byte)_text[i-2];

        return data;
    }

    public static byte[] parseData(short _id, String ... _data)
    {
        ArrayList<Byte> data = new ArrayList<Byte>();
        writeData(data, _id + "");

        for(String _s : _data)
            writeData(data, _s);

        ByteBuf _buf = Unpooled.buffer();
        VarInt.write(_buf, data.size());

        for(int i = 0; i < _buf.capacity(); i++)
            data.add(i, _buf.readByte());

        byte[] _final = new byte[data.size()];
        for(int i = 0; i < data.size(); i++)
            _final[i] = data.get(i);

        return _final;
    }

    private static void writeData(ArrayList<Byte> data, String _data)
    {
        byte[] _parsed = parseString(_data);
        for(byte _b : _parsed)
            data.add(_b);
    }
}
