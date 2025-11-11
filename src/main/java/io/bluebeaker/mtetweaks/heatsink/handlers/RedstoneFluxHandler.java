package io.bluebeaker.mtetweaks.heatsink.handlers;

import io.bluebeaker.mtetweaks.heatsink.HandlerFactory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class RedstoneFluxHandler implements IAccelerableHandler{
    @Override
    public int updateAccelerable(TileEntity tile) {
        if(!tile.hasCapability(CapabilityEnergy.ENERGY,null) || !(tile instanceof ITickable)) return 0;
        IEnergyStorage cap = tile.getCapability(CapabilityEnergy.ENERGY, null);
        if(cap==null) return 0;
        // Cost coolant based on power consumed on work
        int energyPre = cap.getEnergyStored();
        ((ITickable) tile).update();
        return Math.max(0,energyPre-cap.getEnergyStored());
    }
    public static final RedstoneFluxHandler INSTANCE = new RedstoneFluxHandler();

    public static class Factory implements HandlerFactory<RedstoneFluxHandler> {
        @Override
        public RedstoneFluxHandler build(String str) {
            return INSTANCE;
        }
    }
}
