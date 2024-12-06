package io.bluebeaker.mtetweaks;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.item.IPseudoDamageItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class ItemHazmatCharm extends Item implements IPseudoDamageItem, IElectricItem, IBauble {
    public int tickEnergyCost = 100;
    public ItemHazmatCharm(){
        super();
        this.setCreativeTab(CreativeTabs.MISC);
        this.setMaxDamage(27);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }

    @Override
    public boolean canProvideEnergy(ItemStack paramItemStack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack paramItemStack) {
        return 1.0E7D;
    }

    @Override
    public int getTier(ItemStack paramItemStack) {
        return 4;
    }

    @Override
    public double getTransferLimit(ItemStack paramItemStack) {
        return 12000.0D;
    }

    @Override
    public void setStackDamage(ItemStack stack, int damage) {
        super.setDamage(stack, damage);
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new TextComponentTranslation(
            "item.mtetweaks.hazmat_charm.tip").getFormattedText());
    }

    public boolean canUse(ItemStack stack){
        return ElectricItem.manager.canUse(stack, this.tickEnergyCost);
    }

    public void tickPassive(ItemStack stack){
        if(this.canUse(stack)){
            ElectricItem.manager.discharge(stack,this.tickEnergyCost,4,true,false,false);
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.TRINKET;
    }
}
