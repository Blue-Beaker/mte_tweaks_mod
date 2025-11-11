package io.bluebeaker.mtetweaks.heatsink.handlers;

import buildcraft.api.mj.IMjReadable;
import buildcraft.api.mj.MjAPI;
import io.bluebeaker.mtetweaks.MTETweaksConfig;
import io.bluebeaker.mtetweaks.heatsink.HandlerFactory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class BuildCraftHandler implements IAccelerableHandler {
    public static final BuildCraftHandler INSTANCE = new BuildCraftHandler();

    @Override
    public int updateAccelerable(TileEntity tile) {
        if(tile.getCapability(MjAPI.CAP_READABLE,null)==null || !(tile instanceof ITickable)){
            return 0;
        }
        IMjReadable capability = tile.getCapability(MjAPI.CAP_READABLE, null);
        if(capability==null) return 0;
        // Cost coolant based on power consumed on work
        long stored = capability.getStored();
        ((ITickable) tile).update();
        return Math.max(0,(int)Math.ceil((stored - capability.getStored()) * MTETweaksConfig.thermal.accelCostPerMJ / MjAPI.MJ));
    }

    public static class Factory implements HandlerFactory<BuildCraftHandler> {
        @Override
        public BuildCraftHandler build(String str) {
            return INSTANCE;
        }
    }
}
