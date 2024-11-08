package io.bluebeaker.mtetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import io.bluebeaker.mtetweaks.StartupTimer;
import lumien.custommainmenu.lib.StringReplacer;
import zone.rong.mixinextras.injector.ModifyReturnValue;

@Mixin(StringReplacer.class)
public abstract class MixinCMMString {
    @ModifyReturnValue(remap = false,at = @At("RETURN"),method = "replacePlaceholders(Ljava/lang/String;)Ljava/lang/String;")
    private static String replaceAdditionalPlaceholders(String source){
        if(source.contains("#startuptime#")){
            long minutes = StartupTimer.startupTime / 1000L / 60L;
            long seconds = StartupTimer.startupTime / 1000L % 60L;
            return source.replace("#startuptime#", minutes+"m "+seconds+"s");
        }
        return source;
    }
}
