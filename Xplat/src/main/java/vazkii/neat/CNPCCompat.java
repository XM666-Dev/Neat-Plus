package vazkii.neat;

import net.minecraft.world.entity.Entity;
import noppes.npcs.entity.EntityNPCInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CNPCCompat {
    private static List<String> cachedMappings = List.of();
    private static Map<Integer, Integer> cachedColors = Map.of();

    public static Integer getNpcFactionId(Entity entity) {
        if (!(entity instanceof EntityNPCInterface npc)) {
            return null;
        }
        return npc.getFaction().getId();
    }

    public static int getFactionNameColor(Integer factionId, int defaultColor, List<String> mappings) {
        if (factionId == null) {
            return defaultColor;
        }
        return getFactionNameColors(mappings).getOrDefault(factionId, defaultColor);
    }

    private static void reloadFactionNameColors(List<String> mappings) {
        if (Objects.equals(cachedMappings, mappings)) {
            return;
        }
        Map<Integer, Integer> colors = new HashMap<>();
        for (String mapping : mappings) {
            String[] parts = mapping.split("[=:]", 2);
            if (parts.length == 2) {
                try {
                    colors.put(Integer.parseInt(parts[0].trim()),
                            Integer.parseInt(parts[1].trim().replaceFirst("^#", ""), 16));
                } catch (NumberFormatException ignored) {
                    // Ignore malformed mappings.
                }
            }
        }
        cachedMappings = List.copyOf(mappings);
        cachedColors = Map.copyOf(colors);
    }

    private static Map<Integer, Integer> getFactionNameColors(List<String> mappings) {
        reloadFactionNameColors(mappings);
        return cachedColors;
    }
}
