package io.bluebeaker.mtetweaks;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WrenchTweaks {
    private static ItemStack itemSilk = new ItemStack(Items.DIAMOND_PICKAXE);
    static{
        itemSilk.addEnchantment(Enchantments.SILK_TOUCH, 1);
    }
    @SubscribeEvent
    public static void onUseWrench(RightClickBlock event) {
        if (!event.getEntityPlayer().isSneaking())
            return;
        if (!isWrench(event.getItemStack()))
            return;
        IBlockState state = event.getWorld().getBlockState(event.getPos());
        if(isWrenchable(state)){
            state.getBlock().removedByPlayer(state, event.getWorld(), event.getPos(), event.getEntityPlayer(), true);
            state.getBlock().harvestBlock(event.getWorld(), event.getEntityPlayer(), event.getPos(), state,
             event.getWorld().getTileEntity(event.getPos()),itemSilk);
            // event.getWorld().destroyBlock(event.getPos(), true);
        }
    }

    private static boolean isWrench(ItemStack stack) {
        ResourceLocation id = stack.getItem().getRegistryName();
        if (id != null && ConfigHandler.wrenches.containsKey(id)) {
            Set<Integer> allowedMetas = ConfigHandler.wrenches.get(id);
            if (allowedMetas == null || allowedMetas.contains(stack.getMetadata())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param state
     * @return
     */
    private static boolean isWrenchable(IBlockState state) {
        ResourceLocation id = state.getBlock().getRegistryName();
        if (id != null && ConfigHandler.wrenchableBlocks.containsKey(id)) {
            List<Map<String, String>> allowedStates = ConfigHandler.wrenchableBlocks.get(id);
            if (allowedStates == null) {
                return true;
            }
            for (Map<String, String> allowedState : allowedStates) {
                checkStateMatches(state, allowedState);
            }
        }
        return false;
    }

    private static boolean checkStateMatches(IBlockState state, Map<String, String> allowedState) {
        for (IProperty<?> prop : state.getPropertyKeys()) {
            String propName = prop.getName();
            if (allowedState.containsKey(propName)) {
                if (state.getProperties().get(prop).toString() != allowedState.get(propName))
                    return false;
            }
        }
        return true;
    }
}
