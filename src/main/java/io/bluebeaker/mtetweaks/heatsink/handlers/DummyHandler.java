package io.bluebeaker.mtetweaks.heatsink.handlers;

import io.bluebeaker.mtetweaks.heatsink.HandlerFactory;
import net.minecraft.tileentity.TileEntity;

public class DummyHandler implements IAccelerableHandler {
    @Override
    public int updateAccelerable(TileEntity tile) {
        return 0;
    }
    public static final DummyHandler INSTANCE = new DummyHandler();

    public static class Factory implements HandlerFactory<DummyHandler> {
        @Override
        public DummyHandler build(String str) {
            return INSTANCE;
        }
    }
}
