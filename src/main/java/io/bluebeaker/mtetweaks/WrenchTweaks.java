package io.bluebeaker.mtetweaks;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cofh.api.item.IToolHammer;
import ic2.core.ref.IC2Material;
import io.bluebeaker.mtetweaks.wrench.ConfigWrench;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

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
        if (isUsableWrench(stack, event.getEntityPlayer(), event.getPos())) {
            if (event.getState().getMaterial() == IC2Material.MACHINE || event.getState().getMaterial()==IC2Material.PIPE) {
                event.setNewSpeed(event.getOriginalSpeed() / stack.getDestroySpeed(event.getState()) * 4);
            }
        }
        ;
    }

    @SubscribeEvent
    public static void onUseWrench(RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        if (!player.isSneaking())
            return;
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        if (!isUsableWrench(stack, player, pos))
            return;
        World world = event.getWorld();
        IBlockState state = world.getBlockState(pos);
        if (isWrenchable(state)) {
            event.setUseBlock(Result.DENY);
            event.setUseItem(Result.ALLOW);
            player.swingArm(EnumHand.MAIN_HAND);

            // Post the block break event
            BlockEvent.BreakEvent event2 = new BlockEvent.BreakEvent(world, pos, state, player);
            MinecraftForge.EVENT_BUS.post(event2);

            // Handle if the event is canceled
            if (!event2.isCanceled() || event2.getResult() == Result.DENY) {
                if (state.getBlock().removedByPlayer(state, world, pos, player, true))
                    state.getBlock().harvestBlock(world, player, pos, state,
                            world.getTileEntity(pos), itemSilk);
            }
        }
        if (stack.getItem() instanceof IToolHammer) {
            ((IToolHammer) stack.getItem()).toolUsed(stack, player, pos);
        }
    }

    private static boolean isUsableWrench(ItemStack stack, EntityPlayer player, BlockPos pos) {

        if (stack.getItem() instanceof IToolHammer) {
            return ((IToolHammer) stack.getItem()).isUsable(stack, player, pos);
        }
        ResourceLocation id = stack.getItem().getRegistryName();
        if (id != null && ConfigWrench.wrenches.containsKey(id)) {
            Set<Integer> allowedMetas = ConfigWrench.wrenches.get(id);
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
        if (id != null && ConfigWrench.wrenchableBlocks.containsKey(id)) {
            List<Map<String, String>> allowedStates = ConfigWrench.wrenchableBlocks.get(id);
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
