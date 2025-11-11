package io.bluebeaker.mtetweaks.mixin;

import cofh.core.util.helpers.BlockHelper;
import cofh.thermalexpansion.block.device.TileDeviceBase;
import cofh.thermalexpansion.block.device.TileHeatSink;
import io.bluebeaker.mtetweaks.heatsink.HeatSinkHandler;
import io.bluebeaker.mtetweaks.heatsink.handlers.IAccelerableHandler;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cofh.thermalexpansion.block.device.TileHeatSink.USE_FACTOR;

@Mixin(value = TileHeatSink.class,remap = false)
public abstract class MixinTileHeatSink extends TileDeviceBase {
    @Shadow private int coolantRF;
    @Unique
    TileEntity[] mTETweaks$additionalTiles = new TileEntity[6];

    @Inject(method = "updateAdjacentHandlers",at = @At("HEAD"))
    public void updateHandlers(CallbackInfo ci){
        for (int i = 0; i < 6; i++) {
            mTETweaks$additionalTiles[i] = null;
            TileEntity tile = BlockHelper.getAdjacentTileEntity(this, i);
            if(tile!=null && HeatSinkHandler.getHandlerFor(tile)!=null){
                mTETweaks$additionalTiles[i]=tile;
            }
        }
    }

    @Inject(method = "updateAccelerables",at = @At("HEAD"))
    public void updateAdditionalAccelerables(CallbackInfo ci){
        for (int i = 0; i < 6; i++) {
            TileEntity tile = mTETweaks$additionalTiles[i];
            if (tile != null) {
                IAccelerableHandler handler = HeatSinkHandler.getHandlerFor(tile);
                if(handler != null) {
                    coolantRF -= USE_FACTOR * handler.updateAccelerable(tile);
                }
            }
        }
    }
}
