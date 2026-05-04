package com.swill.hitbox;

import net.minecraft.entity.Entity;

public class AttackContext {
    private static Entity currentTarget = null;
    private static int tick = 0;

    public static void setAttacking(Entity entity) {
        currentTarget = entity;
        tick = 2;
    }

    public static boolean isCurrentlyAttacking(Entity entity) {
        if (currentTarget == entity && tick > 0) {
            tick--;
            if (tick == 0) currentTarget = null;
            return true;
        }
        return false;
    }
}
