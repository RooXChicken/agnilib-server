package com.rooxchicken.agnilib.Data;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Target
{
    public Block hitBlock;
    public Entity hitEntity;
    public Vector hitPosition;

    public Target(Block _block, Entity _entity, Vector _pos)
    {
        hitBlock = _block;
        hitEntity = _entity;
        hitPosition = _pos;
    }

    public Target clone()
    {
        return new Target(hitBlock, hitEntity, hitPosition);
    }
}
