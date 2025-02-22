package io.bluebeaker.mtetweaks.blocks;

import io.bluebeaker.mtetweaks.MTETweaksConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRoad extends Block {

    public BlockRoad() {
        super(Material.ROCK);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHardness(1.5f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", -1);
        this.setDefaultSlipperiness((float)(0.6/ MTETweaksConfig.road_walk_speed));
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof EntityLivingBase && !entityIn.isSneaking())
        {
            entityIn.motionX *= MTETweaksConfig.road_walk_speed;
            entityIn.motionZ *= MTETweaksConfig.road_walk_speed;
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }
    
}
