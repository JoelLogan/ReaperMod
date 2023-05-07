package com.whitehallplugins.reapermod.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public interface playerDeathCallback {
    Event<playerDeathCallback> EVENT = EventFactory.createArrayBacked(playerDeathCallback.class,
            (listeners) -> (damageSource, callbackInfo) -> {
                for (playerDeathCallback event : listeners) {
                    ActionResult result = event.interact(damageSource, callbackInfo);
                    System.out.println("EVENT message");
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            }
    );

    ActionResult interact(DamageSource source, CallbackInfo callbackInfo);

}
