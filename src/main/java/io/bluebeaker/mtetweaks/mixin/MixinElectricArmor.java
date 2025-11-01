package io.bluebeaker.mtetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import ic2.core.item.armor.ItemArmorElectric;
import io.bluebeaker.mtetweaks.MTETweaksConfig;
import net.minecraft.item.ItemStack;

@Mixin(ItemArmorElectric.class)
public class MixinElectricArmor {
    @Inject(method = "isBookEnchantable(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z",at = @At("RETURN"),remap = false,cancellable = true)
    public void isBookEnchantable(ItemStack stack, ItemStack book,CallbackInfoReturnable<Boolean> cir){
        if(MTETweaksConfig.etools_enchantable) {
            cir.setReturnValue(true);
        }
    }
}
