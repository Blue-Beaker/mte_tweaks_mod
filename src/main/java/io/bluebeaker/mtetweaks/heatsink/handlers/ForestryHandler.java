package io.bluebeaker.mtetweaks.heatsink.handlers;

import forestry.core.tiles.TilePowered;
import io.bluebeaker.mtetweaks.heatsink.HandlerFactory;
import net.minecraft.tileentity.TileEntity;

public class ForestryHandler implements IAccelerableHandler{
    @Override
    public int updateAccelerable(TileEntity tile) {
        if (!(tile instanceof TilePowered)) {
            return 0;
        }

        TilePowered tilePowered = (TilePowered) tile;

        if(!tilePowered.hasWork()) return 0;

        int power = (int) Math.ceil((float)tilePowered.getEnergyPerWorkCycle() / tilePowered.getTicksPerWorkCycle());

        if(tilePowered.getEnergyManager().getEnergyStored()<power) return 0;

        tilePowered.update();
        return power;
    }
    public static final ForestryHandler INSTANCE = new ForestryHandler();

    public static class Factory implements HandlerFactory<ForestryHandler> {
        @Override
        public ForestryHandler build(String str) {
            return INSTANCE;
        }
    }
}
