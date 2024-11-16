package net.tabby.ffr.Item.tool.unique;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tabby.ffr.Item.tool.AbstractTool;
import net.tabby.ffr.event.ev;
import net.tabby.ffr.registry.ii;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class KnifeItem extends AbstractTool {
    public KnifeItem(ToolMaterial material) { //# TODO: for 5) does not work as a knife or attack-like item, deals 0 extra damage, for 9) tool doesn't take durability when mining grass, major flaw, infinite uses
        super(material);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        Random ran = new Random();
        if (!worldIn.isRemote) { // if server-side
            if (state.getBlock() instanceof BlockLeaves) {
                if (ran.nextFloat() >= 0.45f) {
                    EntityItem eni = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.STICK, (int) ev.next(ran, 0f, 1.7f)));
                    eni.setDefaultPickupDelay();
                    worldIn.spawnEntity(eni);
                }
            }
        }
        if (!worldIn.isRemote) {
            if (state.getMaterial() == Material.VINE) {
                EntityItem eni = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ii.GRASS_FIBER, (int) ev.next(new Random(), 1f, 3.2f)));
                eni.setDefaultPickupDelay();
                worldIn.spawnEntity(eni);
            }
        }
        return true;
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
