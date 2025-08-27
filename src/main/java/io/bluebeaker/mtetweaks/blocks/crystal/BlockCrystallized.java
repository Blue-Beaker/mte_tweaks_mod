package io.bluebeaker.mtetweaks.blocks.crystal;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCrystallized extends Block {
    public BlockCrystallized() {
        super(Material.GLASS);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(1.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 0);
    }
    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return 15;
    }
}
