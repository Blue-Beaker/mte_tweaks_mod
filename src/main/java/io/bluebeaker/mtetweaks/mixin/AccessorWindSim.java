package io.bluebeaker.mtetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import ic2.core.WindSim;
import net.minecraft.world.World;

@Mixin(WindSim.class)
public interface AccessorWindSim {
    @Accessor(remap = false)
    public World getWorld();
}
