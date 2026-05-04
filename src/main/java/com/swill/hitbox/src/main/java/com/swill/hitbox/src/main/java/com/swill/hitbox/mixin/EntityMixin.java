package com.swill.hitbox.mixin;

import com.swill.hitbox.AttackContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract Box getBoundingBox();

    @Inject(method = "getBoundingBox", at = @At("RETURN"), cancellable = true)
    private void onGetBoundingBox(CallbackInfoReturnable<Box> cir) {
        Entity self = (Entity)(Object)this;
        if (AttackContext.isCurrentlyAttacking(self)) {
            Box original = cir.getReturnValue();
            double width = original.maxX - original.minX;
            double height = original.maxY - original.minY;
            double depth = original.maxZ - original.minZ;
            Box expanded = original.expand(width * 1.5, height * 1.5, depth * 1.5);
            cir.setReturnValue(expanded);
        }
    }
}
