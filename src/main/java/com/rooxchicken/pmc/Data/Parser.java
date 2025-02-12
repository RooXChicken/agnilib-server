package com.rooxchicken.pmc.Data;

public class Parser
{
    public static byte[] parseString(byte _id, String _data)
    {
        // convert string to char array
        char[] _text = _data.toCharArray();

        // create a data buffer with the size of the char array, with the plus one leaving room for the size byte
        byte[] data = new byte[_text.length+3];
        
        // tell nms the size of the byte array
        data[0] = (byte)(data.length-1);
        data[1] = _id;
        data[2] = (byte)_text.length;

        // fill in the data
        for(int i = 3; i < _text.length+3; i++)
            data[i] = (byte)_text[i-3];

        return data;
    }

    public static byte[] parseInt(byte _id, int _data)
    {
        return parseString(_id, _data + "");
    }
}
