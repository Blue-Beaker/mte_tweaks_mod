package io.bluebeaker.mtetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.bluebeaker.mtetweaks.StartupTimer;
import lumien.custommainmenu.lib.StringReplacer;

@Mixin(StringReplacer.class)
public abstract class MixinCMMString {
    @Inject(remap = false,at = @At("RETURN"),method = "replacePlaceholders(Ljava/lang/String;)Ljava/lang/String;",cancellable = true)
    private static void replaceAdditionalPlaceholders(String source,CallbackInfoReturnable<String> cir){
        String original=cir.getReturnValue();
        if(original.contains("#startuptime#")){
            long minutes = StartupTimer.startupTime / 1000L / 60L;
            long seconds = StartupTimer.startupTime / 1000L % 60L;
            cir.setReturnValue(original.replace("#startuptime#", minutes+"m "+seconds+"s"));
        }
    }
}
