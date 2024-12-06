package io.bluebeaker.mtetweaks.jei;

import io.bluebeaker.mtetweaks.MTETweaksItems;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;

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
        modRegistry.addIngredientInfo(new ItemStack(MTETweaksItems.HAZMAT_CHARM),VanillaTypes.ITEM,"item.mtetweaks.hazmat_charm.info");
    }
}
