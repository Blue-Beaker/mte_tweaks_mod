package io.bluebeaker.mtetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import ic2.core.WindSim;
import io.bluebeaker.mtetweaks.ConfigHandler;
import net.minecraft.world.World;

@Mixin(WindSim.class)
public class MixinWindSim {
    @Inject(method = "getWindAt(D)D",at = @At("HEAD"),cancellable = true,remap = false)
    public void getWindAt(double height, CallbackInfoReturnable<Double> cir) {
        // int dimensionID = ((AccessorWindSim)(Object)this).getWorld().provider.getDimension();
        int dimensionID = this.world.provider.getDimension();
        if(ConfigHandler.windlessDims.contains(dimensionID)){
            cir.setReturnValue(0.0d);
        }
    }
    @Shadow(remap = false)
    World world;
}
