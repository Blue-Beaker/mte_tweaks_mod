package io.bluebeaker.mtetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import ic2.core.block.generator.tileentity.TileEntityWindGenerator;

@Mixin(TileEntityWindGenerator.class)
public class MixinWindGenerator {
    @Inject(remap = false, method = "<init>()V", at = @At(value = "RETURN"),cancellable = false)
    private void windGenerator(CallbackInfo ci) {
        ((AccessorWindGenerator)(Object)this).setTicker(127);
    }
}
