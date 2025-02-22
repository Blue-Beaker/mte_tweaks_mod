package io.bluebeaker.mtetweaks.items;

import io.bluebeaker.mtetweaks.MTETweaksMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MTETweaksItems {
    public static final ItemHazmatCharm HAZMAT_CHARM = (ItemHazmatCharm) addItem(new ItemHazmatCharm(),"hazmat_charm");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(HAZMAT_CHARM);
        ModelLoader.setCustomModelResourceLocation(HAZMAT_CHARM,0,new ModelResourceLocation(HAZMAT_CHARM.getRegistryName(),"inventory"));
    }

    private static Item addItem(Item item, String id){
        item.setRegistryName(MTETweaksMod.MODID,id);
        item.setTranslationKey(MTETweaksMod.MODID+"."+id);
        return item;
    }
}
