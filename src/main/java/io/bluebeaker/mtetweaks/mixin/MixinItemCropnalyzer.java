package io.bluebeaker.mtetweaks.mixin;

import ic2.core.item.tool.ItemCropnalyzer;
import io.bluebeaker.mtetweaks.MTETweaksConfig;
import io.bluebeaker.mtetweaks.crop.CropInfoHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemCropnalyzer.class,remap = false)
public abstract class MixinItemCropnalyzer {
    @Inject(method = "onItemUseFirst(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;FFFLnet/minecraft/util/EnumHand;)Lnet/minecraft/util/EnumActionResult;",at = @At("RETURN"))
    public void addCCropInfo(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand, CallbackInfoReturnable<EnumActionResult> cir){
        if(MTETweaksConfig.ic2.moreCropInfo && cir.getReturnValue()==EnumActionResult.SUCCESS){
            CropInfoHandler.onUseCropnalyzer(player,world.getTileEntity(pos));
        }
    }
}
