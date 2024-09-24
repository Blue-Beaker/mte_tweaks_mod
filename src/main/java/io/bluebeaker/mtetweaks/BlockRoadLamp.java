package io.bluebeaker.mtetweaks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRoadLamp extends BlockRoad {

    private final boolean isPowered;
    private final boolean inverted;

    public BlockRoadLamp(boolean isLit, boolean inverted) {
        super();
        this.isPowered = isLit ^ inverted;
        this.inverted = inverted;
        this.setCreativeTab(CreativeTabs.REDSTONE);
        if (isLit) {
            this.setLightLevel(1.0F);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.doUpdate(worldIn, pos);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        this.doUpdate(worldIn, pos);
    }

    public void doUpdate(World worldIn, BlockPos pos) {

        if (this.isPowered && !worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, this.inverted ? MTETweaksBlocksRegistry.ROAD_LAMP_INVERT_LIT.getDefaultState()
                    : MTETweaksBlocksRegistry.ROAD_LAMP.getDefaultState(), 2);

        } else if (!this.isPowered && worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, this.inverted ? MTETweaksBlocksRegistry.ROAD_LAMP_INVERT.getDefaultState()
                    : MTETweaksBlocksRegistry.ROAD_LAMP_LIT.getDefaultState(), 2);
        }

    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this.inverted?MTETweaksBlocksRegistry.ROAD_LAMP_INVERT:MTETweaksBlocksRegistry.ROAD_LAMP);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this.inverted?MTETweaksBlocksRegistry.ROAD_LAMP_INVERT:MTETweaksBlocksRegistry.ROAD_LAMP);
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(this.inverted?MTETweaksBlocksRegistry.ROAD_LAMP_INVERT:MTETweaksBlocksRegistry.ROAD_LAMP);
    }
    
}
