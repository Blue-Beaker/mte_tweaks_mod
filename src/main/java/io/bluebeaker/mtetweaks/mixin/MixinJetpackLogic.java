package io.bluebeaker.mtetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import ic2.core.IC2;
import ic2.core.item.armor.jetpack.IJetpack;
import ic2.core.item.armor.jetpack.JetpackLogic;
import io.bluebeaker.mtetweaks.MTETweaksConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

@Mixin(value = JetpackLogic.class,remap = false)
public class MixinJetpackLogic {
    @Inject(method = "useJetpack",at = @At(value = "FIELD",target="Lnet/minecraft/entity/player/EntityPlayer;field_70181_x:D"),cancellable = true)
    private static void useJetpackOnElytra(EntityPlayer player, boolean hoverMode, IJetpack jetpack, ItemStack stack,CallbackInfoReturnable<Boolean> cir){
        if(hoverMode || MTETweaksConfig.jetpack_elytra_boost<=0){
            return;
        }
        if(player.isElytraFlying() && IC2.keyboard.isForwardKeyDown(player)){
            Vec3d vec3d = player.getLookVec();
            double boost = MTETweaksConfig.jetpack_elytra_boost;
            player.motionX += vec3d.x * boost*0.1D + (vec3d.x * 1.5D - player.motionX) * boost;
            player.motionY += vec3d.y * boost*0.1D + (vec3d.y * 1.5D - player.motionY) * boost;
            player.motionZ += vec3d.z * boost*0.1D + (vec3d.z * 1.5D - player.motionZ) * boost;
            cir.setReturnValue(true);
        }
    }
    @Inject(method = "useJetpack",at = @At("HEAD"), cancellable = true)
    private static void cancelJetpackOnFlying(EntityPlayer player, boolean hoverMode, IJetpack jetpack, ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        if(MTETweaksConfig.ic2.disable_jetpack_flying && player.capabilities.isFlying) {
            cir.setReturnValue(false);
        }
    }
}
