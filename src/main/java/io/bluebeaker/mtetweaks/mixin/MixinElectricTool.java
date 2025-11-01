package io.bluebeaker.mtetweaks.mixin;

import ic2.core.item.tool.*;
import ic2.core.ref.ItemName;
import io.bluebeaker.mtetweaks.MTETweaksConfig;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(ItemElectricTool.class)
public abstract class MixinElectricTool extends ItemTool {

    protected MixinElectricTool(ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(materialIn, effectiveBlocksIn);
    }
    
    @Inject(method = "<init>(Lic2/core/ref/ItemName;ILic2/core/item/tool/HarvestLevel;Ljava/util/Set;)V",at = @At("RETURN"),remap = false)
    public void changeSpeed(ItemName name, int operationEnergyCost, HarvestLevel harvestLevel, Set<? extends IToolClass> toolClasses,CallbackInfo ci){
        if(((ItemElectricTool)(Object)this) instanceof ItemElectricToolChainsaw || ((ItemElectricTool)(Object)this) instanceof ItemDrill){
            this.attackSpeed=MTETweaksConfig.etool_attack_speed;
        }else{
            this.attackSpeed=MTETweaksConfig.etool_attack_speed_2;
        }
    }
    
    @Inject(method = "isBookEnchantable(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z",at = @At("RETURN"),remap = false,cancellable = true)
    public void isBookEnchantable(ItemStack stack, ItemStack book,CallbackInfoReturnable<Boolean> cir){
        if(MTETweaksConfig.etools_enchantable && !(((ItemElectricTool)(Object)this) instanceof ItemDrillIridium)) {
            cir.setReturnValue(true);
        }
    }
}
