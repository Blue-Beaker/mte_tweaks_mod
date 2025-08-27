package io.bluebeaker.mtetweaks.blocks.crystal;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileCrystallizable extends TileEntity {

    private int stage;
    private float growthModifier;

    public TileCrystallizable(){
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("GrowthStage", this.stage);
        compound.setFloat("GrowthModifier", this.growthModifier);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.stage = compound.getInteger("GrowthStage");
        this.growthModifier = compound.getFloat("GrowthModifier");
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public float getGrowthModifier() {
        return growthModifier;
    }

    public void setGrowthModifier(float growthModifier) {
        this.growthModifier = growthModifier;
    }
}
