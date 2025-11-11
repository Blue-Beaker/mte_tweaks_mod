package io.bluebeaker.mtetweaks;

import net.minecraftforge.fml.common.Loader;

public enum ModChecker {
    buildcraftcore("buildcraftcore"),
    buildcrafttransport("buildcrafttransport"),
    forestry("forestry"),
    ic2("ic2"),
    thermalexpansion("thermalexpansion");

    public final String modid;
    private boolean isLoaded = false;

    ModChecker(String modid) {
        this.modid = modid;
    }

    public boolean isLoaded() {
        if (this.isLoaded)
            return true;
        this.isLoaded = Loader.isModLoaded(this.modid);
        return this.isLoaded;
    }

    public String getVersion() {
        return Loader.instance().getIndexedModList().get(this.modid).getVersion();
    }
}
