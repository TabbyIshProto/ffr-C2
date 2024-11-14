package net.tabby.ffr.Item.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import org.jetbrains.annotations.NotNull;

public class KnifeItem extends ItemSword {
    public KnifeItem(ToolMaterial material) {
        super(material);
    }

    @Override
    public boolean hasContainerItem(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public @NotNull ItemStack getContainerItem(@NotNull ItemStack stc) {
        stc = stc.copy();
        stc.attemptDamageItem(1, itemRand, null);
        return stc;
    }
}
