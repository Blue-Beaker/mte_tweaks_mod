package io.bluebeaker.mtetweaks.items;

import io.bluebeaker.mtetweaks.MTETweaksConfig;
import io.bluebeaker.mtetweaks.MTETweaksMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MTETweaksItems {
    public static ItemHazmatCharm HAZMAT_CHARM;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        if(MTETweaksConfig.ic2.hazmat_charm){
            HAZMAT_CHARM = (ItemHazmatCharm) addItem(new ItemHazmatCharm(),"hazmat_charm");
            event.getRegistry().register(HAZMAT_CHARM);
            if(!MTETweaksMod.isServer()){
                ModelLoader.setCustomModelResourceLocation(HAZMAT_CHARM,0,new ModelResourceLocation(HAZMAT_CHARM.getRegistryName(),"inventory"));
            }
        }
    }
    private static Item addItem(Item item, String id){
        item.setRegistryName(MTETweaksMod.MODID,id);
        item.setTranslationKey(MTETweaksMod.MODID+"."+id);
        return item;
    }
}
