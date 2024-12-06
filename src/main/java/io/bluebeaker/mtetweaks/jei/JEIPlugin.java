package io.bluebeaker.mtetweaks.jei;

import io.bluebeaker.mtetweaks.ItemHazmatCharm;
import io.bluebeaker.mtetweaks.MTETweaksItems;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
    public static IModRegistry modRegistry;
    public static IJeiHelpers jeiHelpers;
    @Override
    public void register(IModRegistry registry) {
        jeiHelpers = registry.getJeiHelpers();
        modRegistry = registry;
        addDescription();
    }

    public void addDescription(){
        modRegistry.addIngredientInfo(new ItemStack(MTETweaksItems.HAZMAT_CHARM),VanillaTypes.ITEM, I18n.format("item.mtetweaks.hazmat_charm.info", ItemHazmatCharm.getTickEnergyCost()));
    }
}
