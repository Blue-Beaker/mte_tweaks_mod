package io.bluebeaker.mtetweaks.blocks.crystal;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockCrystallizable extends BlockContainer {
    int totalStages = 15;
    protected BlockCrystallizable() {
        super(Material.GLASS);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(1.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TileCrystallizable tileCrystallizable = new TileCrystallizable();
        tileCrystallizable.setGrowthModifier(0);
        tileCrystallizable.setStage(0);
        return tileCrystallizable;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TileCrystallizable){
            return ((TileCrystallizable)tileEntity).getStage()*15/totalStages;
        }
        return 0;
    }
}
