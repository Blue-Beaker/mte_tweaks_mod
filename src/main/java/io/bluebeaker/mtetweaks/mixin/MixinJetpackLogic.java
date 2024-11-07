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

@Mixin(JetpackLogic.class)
public class MixinJetpackLogic {
    @Inject(remap = false,method = "useJetpack",at = @At(value = "FIELD",target="Lnet/minecraft/entity/player/EntityPlayer;field_70181_x"),cancellable = true)
    private static void useJetpackOnElytra(EntityPlayer player, boolean hoverMode, IJetpack jetpack, ItemStack stack,CallbackInfoReturnable<Boolean> cir){
        if(hoverMode || !MTETweaksConfig.jetpack_boost_forward){
            return;
        }
        if(player.isElytraFlying() && IC2.keyboard.isForwardKeyDown(player)){
            Vec3d vec3d = player.getLookVec();
            player.motionX += vec3d.x * 0.05D + (vec3d.x * 2.5D - player.motionX) * 0.05D;
            player.motionY += vec3d.y * 0.05D + (vec3d.y * 2.5D - player.motionY) * 0.05D;
            player.motionZ += vec3d.z * 0.05D + (vec3d.z * 2.5D - player.motionZ) * 0.05D;
            cir.setReturnValue(true);
        }
    }
}
