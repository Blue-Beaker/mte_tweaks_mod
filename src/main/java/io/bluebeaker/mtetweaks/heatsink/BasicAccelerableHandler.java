package io.bluebeaker.mtetweaks.heatsink;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class BasicAccelerableHandler implements IAccelerableHandler {
    private final int cost;

    public BasicAccelerableHandler(int cost){
        this.cost = cost;
    }
    @Override
    public int updateAccelerable(TileEntity tile) {
        if(tile instanceof ITickable){
            ((ITickable) tile).update();
        }
        return cost;
    }
}
