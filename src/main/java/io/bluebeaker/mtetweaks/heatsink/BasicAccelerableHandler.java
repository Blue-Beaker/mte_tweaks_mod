package io.bluebeaker.mtetweaks.heatsink;

import io.bluebeaker.mtetweaks.MTETweaksMod;
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

    public static class Factory implements HandlerFactory<BasicAccelerableHandler>{
        @Override
        public BasicAccelerableHandler build(String str) {
            int cost=0;
            try {
                cost=Integer.parseInt(str);
            } catch (NumberFormatException e) {
                MTETweaksMod.getLogger().warn("Error parsing integer from '{}':",str,e);
            }
            return new BasicAccelerableHandler(cost);
        }
    }
}
