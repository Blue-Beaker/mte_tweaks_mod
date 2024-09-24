package io.bluebeaker.mtetweaks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class MTETweaksBlocksRegistry {
    private static MTETweaksBlocksRegistry INSTANCE;

    public static final Block ROAD_BLOCK = addBlock(new BlockRoad(), "road_block");
    public static final Block ROAD_LAMP = addBlock(new BlockRoadLamp(false, false), "road_lamp");
    public static final Block ROAD_LAMP_LIT = addBlock(new BlockRoadLamp(true, false), "road_lamp_lit");
    public static final Block ROAD_LAMP_INVERT = addBlock(new BlockRoadLamp(false, true), "road_lamp_invert");
    public static final Block ROAD_LAMP_INVERT_LIT = addBlock(new BlockRoadLamp(true, true), "road_lamp_invert_lit");

    public MTETweaksBlocksRegistry(RegistryEvent.Register<Block> event) {
        INSTANCE = this;
        event.getRegistry().register(ROAD_BLOCK);
        event.getRegistry().register(ROAD_LAMP);
        event.getRegistry().register(ROAD_LAMP_LIT);
        event.getRegistry().register(ROAD_LAMP_INVERT);
        event.getRegistry().register(ROAD_LAMP_INVERT_LIT);
    }

    private static Block addBlock(Block block, String id) {
        block.setRegistryName(MTETweaksMod.MODID, id);
        block.setTranslationKey(MTETweaksMod.MODID+"."+id);
        return block;
    }

    public static MTETweaksBlocksRegistry getInstance() {
        return INSTANCE;
    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        Item ROAD_BLOCK_ITEM=new ItemBlock(ROAD_BLOCK).setRegistryName(ROAD_BLOCK.getRegistryName());
        Item ROAD_LAMP_ITEM=new ItemBlock(ROAD_LAMP).setRegistryName(ROAD_LAMP.getRegistryName());
        Item ROAD_LAMP_INVERT_ITEM=new ItemBlock(ROAD_LAMP_INVERT_LIT).setRegistryName(ROAD_LAMP_INVERT_LIT.getRegistryName());
        ModelLoader.setCustomModelResourceLocation(ROAD_BLOCK_ITEM,0,new ModelResourceLocation(ROAD_BLOCK.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ROAD_LAMP_ITEM,0,new ModelResourceLocation(ROAD_LAMP.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ROAD_LAMP_INVERT_ITEM,0,new ModelResourceLocation(ROAD_LAMP_INVERT_LIT.getRegistryName(),"inventory"));
        event.getRegistry().register(ROAD_BLOCK_ITEM);
        event.getRegistry().register(ROAD_LAMP_ITEM);
        event.getRegistry().register(ROAD_LAMP_INVERT_ITEM);
    }
}
