package io.bluebeaker.mtetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At;

import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.machine.tileentity.TileEntityMassFabricator;
import ic2.core.item.type.MiscResourceType;
import ic2.core.ref.ItemName;

@Mixin(TileEntityMassFabricator.class)
public class MixinMassFabricator {
    @ModifyVariable(remap = false, method = "updateEntityServer()V", at = @At(value = "LOAD"),ordinal = 0)
    private double ifScrapFull(double scrapConversion) {
        if(this.consumedScrap >= REQUIRED_SCRAP && this.outputSlot.canAdd(ItemName.misc_resource.getItemStack(MiscResourceType.matter))){
            return Math.max(scrapConversion,0.001);
        }
        return scrapConversion;
    }
    @Shadow(remap = false)
    int consumedScrap;
    @Shadow(remap = false)
    static int REQUIRED_SCRAP;
    @Shadow(remap = false)
    InvSlotOutput outputSlot;
}
