package io.bluebeaker.mtetweaks.heatsink;

import io.bluebeaker.mtetweaks.heatsink.handlers.IAccelerableHandler;

public interface HandlerFactory<T extends IAccelerableHandler> {
    /** Build and return a handler.
     * @param str Parameter for the handler
     * @return The built handler
     */
    T build(String str);
}
