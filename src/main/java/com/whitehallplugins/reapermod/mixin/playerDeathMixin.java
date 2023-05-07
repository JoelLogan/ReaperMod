package com.whitehallplugins.reapermod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
//import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.whitehallplugins.reapermod.events.playerDeathCallback;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class playerDeathMixin {

    @Inject(at = @At(value = "HEAD"), method = "onDeath", cancellable = true)
    private void onDeath(final DamageSource damageSource, final CallbackInfo ci) {
        ActionResult result = playerDeathCallback.EVENT.invoker().interact(damageSource, ci);
        System.out.println("Mixin Message");
        if(result == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
