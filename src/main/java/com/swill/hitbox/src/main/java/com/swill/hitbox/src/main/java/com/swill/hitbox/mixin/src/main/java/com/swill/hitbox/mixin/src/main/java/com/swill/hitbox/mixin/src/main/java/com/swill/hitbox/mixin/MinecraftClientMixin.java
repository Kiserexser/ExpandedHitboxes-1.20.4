package com.swill.hitbox.mixin;

import com.swill.hitbox.AttackContext;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "doAttack", at = @At("HEAD"))
    private void onDoAttack(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient)(Object)this;
        if (client.targetedEntity != null) {
            AttackContext.setAttacking(client.targetedEntity);
        }
    }
}
