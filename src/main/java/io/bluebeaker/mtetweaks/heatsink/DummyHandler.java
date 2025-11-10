package io.bluebeaker.mtetweaks.heatsink;

import net.minecraft.tileentity.TileEntity;

public class DummyHandler implements IAccelerableHandler{
    @Override
    public int updateAccelerable(TileEntity tile) {
        return 0;
    }

    public static class Factory implements HandlerFactory<DummyHandler>{
        @Override
        public DummyHandler build(String str) {
            return new DummyHandler();
        }
    }
}
