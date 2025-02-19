package com.rooxchicken.pmc.Data;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.VarInt;

public class Parser
{
    public static void writeString(String _string, ByteBuf _buf)
    {
        _buf.writeShort(_string.trim().length());
        _buf.writeCharSequence(_string, Charset.defaultCharset());
    }
}
