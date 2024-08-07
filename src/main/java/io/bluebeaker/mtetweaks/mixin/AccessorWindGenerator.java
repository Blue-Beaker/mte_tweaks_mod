package io.bluebeaker.mtetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import ic2.core.block.generator.tileentity.TileEntityWindGenerator;

@Mixin(TileEntityWindGenerator.class)
public interface AccessorWindGenerator {
    @Accessor(remap = false)
    public void setTicker(int ticker);
}
