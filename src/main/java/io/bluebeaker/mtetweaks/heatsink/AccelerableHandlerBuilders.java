package io.bluebeaker.mtetweaks.heatsink;

public enum AccelerableHandlerBuilders {
    BASIC(new BasicAccelerableHandler.Factory()),
    DUMMY(new DummyHandler.Factory());

    private final HandlerFactory<? extends IAccelerableHandler> factory;

    AccelerableHandlerBuilders(HandlerFactory<? extends IAccelerableHandler> factory){
        this.factory = factory;
    }
    public IAccelerableHandler build(String str){
        return factory.build(str);
    }

    public static IAccelerableHandler getForName(String str){
        String[] split = str.split(":",2);
        if(split.length>1){
            return AccelerableHandlerBuilders.valueOf(split[0].toUpperCase()).build(split[1]);
        }
        return BASIC.build(str);
    }
}
