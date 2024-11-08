package io.bluebeaker.mtetweaks;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cofh.api.item.IToolHammer;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WrenchTweaks {
    private static ItemStack itemSilk = new ItemStack(Items.DIAMOND_PICKAXE);
    static {
        itemSilk.addEnchantment(Enchantments.SILK_TOUCH, 1);
    }

    @SubscribeEvent
    public static void onBreakWithWrench(BreakSpeed event) {
        if (MTETweaksConfig.cofh_wrench_on_ic2_machines == 1.0f)
            return;
        ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
        if (isWrench(stack, event.getEntityPlayer(), event.getPos())) {
            if (event.getState().getBlock().getHarvestTool(event.getState()) == "wrench") {
                event.setNewSpeed(event.getOriginalSpeed() / stack.getDestroySpeed(event.getState()) * 4);
            }
        }
        ;
    }

    @SubscribeEvent
    public static void onUseWrench(RightClickBlock event) {
        if (!event.getEntityPlayer().isSneaking())
            return;
        ItemStack stack = event.getItemStack();
        if (!isWrench(stack, event.getEntityPlayer(), event.getPos()))
            return;

        IBlockState state = event.getWorld().getBlockState(event.getPos());
        if (isWrenchable(state)) {
            event.setUseBlock(Result.DENY);
            event.setUseItem(Result.ALLOW);
            event.getEntityPlayer().swingArm(EnumHand.MAIN_HAND);
            state.getBlock().removedByPlayer(state, event.getWorld(), event.getPos(), event.getEntityPlayer(), true);
            state.getBlock().harvestBlock(event.getWorld(), event.getEntityPlayer(), event.getPos(), state,
                    event.getWorld().getTileEntity(event.getPos()), itemSilk);
        }
        if (stack.getItem() instanceof IToolHammer) {
            ((IToolHammer) stack.getItem()).toolUsed(stack, event.getEntityPlayer(), event.getPos());
        }
    }

    private static boolean isWrench(ItemStack stack, EntityPlayer player, BlockPos pos) {

        if (stack.getItem() instanceof IToolHammer) {
            return ((IToolHammer) stack.getItem()).isUsable(stack, player, pos);
        }
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
                if (checkStateMatches(state, allowedState))
                    return true;
            }
        }
        return false;
    }

    private static <T extends Comparable<T>> boolean checkStateMatches(IBlockState state,
            Map<String, String> allowedState) {
        if (MTETweaksConfig.log_debug)
            MTETweaksMod.getLogger().info(allowedState.toString());
        for (Map.Entry<IProperty<?>, Comparable<?>> item : state.getProperties().entrySet()) {
            IProperty<T> prop = (IProperty<T>) item.getKey();
            String propName = prop.getName();
            if (MTETweaksConfig.log_debug)
                MTETweaksMod.getLogger().info(propName + "=" + prop.getName((T) item.getValue()));
            if (allowedState.containsKey(propName)) {
                if (!prop.getName((T) item.getValue()).equals(allowedState.get(propName)))
                    return false;
            }
        }
        return true;
    }
}
