package io.bluebeaker.mtetweaks.heatsink;

import io.bluebeaker.mtetweaks.MTETweaksConfig;
import io.bluebeaker.mtetweaks.MTETweaksMod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class HeatSinkHandler {
    public static Map<ResourceLocation,IAccelerableHandler> handlers = new HashMap<>();
    public static void updateConfig(){
        for (String acceleratableTile : MTETweaksConfig.thermal.acceleratableTiles) {
            String[] split = acceleratableTile.split("=", 2);
            if(split.length<2) continue;
            try {
                ResourceLocation res = new ResourceLocation(split[0]);
                handlers.put(res,AccelerableHandlerBuilders.getForName(split[1]));
            } catch (Exception e) {
                MTETweaksMod.getLogger().info("Exception loading acceleratableTiles entry:",e);
            }
        }
    }

    @Nullable
    public static IAccelerableHandler getHandlerFor(TileEntity tile){
        ResourceLocation key = TileEntity.getKey(tile.getClass());
        if(key==null) return null;
        return handlers.get(key);
    }
}
