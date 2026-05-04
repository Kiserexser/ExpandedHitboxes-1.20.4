package com.swill.hitbox.mixin;

import com.swill.hitbox.AttackContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "sendPacket", at = @At("HEAD"))
    private void onSendPacket(net.minecraft.network.packet.Packet<?> packet, CallbackInfo ci) {
        if (packet instanceof PlayerInteractEntityC2SPacket) {
            try {
                Field entityIdField = PlayerInteractEntityC2SPacket.class.getDeclaredField("entityId");
                entityIdField.setAccessible(true);
                int entityId = (int) entityIdField.get(packet);
                if (MinecraftClient.getInstance().world != null) {
                    Entity target = MinecraftClient.getInstance().world.getEntityById(entityId);
                    if (target != null) {
                        AttackContext.setAttacking(target);
                    }
                }
            } catch (Exception ignored) {}
        }
    }
}
