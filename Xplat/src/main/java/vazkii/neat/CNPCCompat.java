package vazkii.neat;

import net.minecraft.world.entity.Entity;
import noppes.npcs.entity.EntityNPCInterface;

public class CNPCCompat {
    public static Integer getNpcFactionId(Entity entity) {
        if (!(entity instanceof EntityNPCInterface npc)) {
            return null;
        }
        return npc.getFaction().getId();
    }
}
