package io.bluebeaker.mtetweaks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nullable;

public class MTETweaksWorldData extends WorldSavedData {
    public static final String DATA_NAME = MTETweaksMod.MODID + "_data";

    public static final String defRulesApplied_NAME = "defRulesApplied";
    private boolean defRulesApplied = false;

    public MTETweaksWorldData(){
        this(DATA_NAME);
    }
    public MTETweaksWorldData(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        if (nbt.getTagId(DATA_NAME) == 10) {
            NBTTagCompound data = nbt.getCompoundTag(DATA_NAME);
            setDefRulesApplied(data.getBoolean(defRulesApplied_NAME));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        NBTTagCompound data;
        if (!nbt.hasKey(DATA_NAME) || nbt.getTagId(DATA_NAME) != 10) {
            data=new NBTTagCompound();
            nbt.setTag(DATA_NAME,data);
        }else {
            data=nbt.getCompoundTag(DATA_NAME);
        }
        data.setBoolean(defRulesApplied_NAME, isDefRulesApplied());
        return nbt;
    }
    public static @Nullable MTETweaksWorldData get(World world){

        MapStorage storage = world.getMapStorage();
        if(storage==null) return null;
        MTETweaksWorldData instance = (MTETweaksWorldData) storage.getOrLoadData(MTETweaksWorldData.class,DATA_NAME);
        if (instance == null) {
            instance = new MTETweaksWorldData();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

    public boolean isDefRulesApplied() {
        return defRulesApplied;
    }

    public void setDefRulesApplied(boolean defRulesApplied) {
        this.markDirty();
        this.defRulesApplied = defRulesApplied;
    }
}
