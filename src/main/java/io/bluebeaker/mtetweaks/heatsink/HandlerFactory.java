package io.bluebeaker.mtetweaks.heatsink;

public interface HandlerFactory<T> {
    T build(String str);
}
