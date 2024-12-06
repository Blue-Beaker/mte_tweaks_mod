package io.bluebeaker.mtetweaks.mixin;

import ic2.core.item.armor.ItemArmorHazmat;
import io.bluebeaker.mtetweaks.HazmatCharmLogic;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemArmorHazmat.class,remap = false)
public abstract class MixinItemArmorHazmat {
    @Inject(method = "hasCompleteHazmat(Lnet/minecraft/entity/EntityLivingBase;)Z",at=@At("RETURN"),cancellable = true)
    private static void addRadiationCharm(EntityLivingBase living, CallbackInfoReturnable<Boolean> cir){
        if(cir.getReturnValue()) return;
        if(HazmatCharmLogic.onRadiation(living))
            cir.setReturnValue(true);
    }
}
