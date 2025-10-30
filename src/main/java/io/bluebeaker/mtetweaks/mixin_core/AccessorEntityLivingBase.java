package io.bluebeaker.mtetweaks.mixin_core;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface AccessorEntityLivingBase {
    @Invoker("setFlag")
    void invokeSetFlag(int flag, boolean set);

    @Invoker("getFlag")
    boolean invokeGetFlag(int flag);
}
