package net.tabby.ffr.Item.tool;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.tabby.ffr.FloraFaunaRebalance;
import net.tabby.ffr.Tags;

public class Tier {
    public static ToolMaterial FLINT = EnumHelper.addToolMaterial("flint", 0, 39, 2.0F, 0.5F, 15);
    public static void init() {
        FloraFaunaRebalance.LOGGER.info(Tags.MODNAME + " is adding Flint tier to tools");
    }
}
