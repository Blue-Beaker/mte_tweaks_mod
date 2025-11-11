package io.bluebeaker.mtetweaks.heatsink;

import io.bluebeaker.mtetweaks.heatsink.handlers.IAccelerableHandler;

public interface HandlerFactory<T extends IAccelerableHandler> {
    T build(String str);
}
