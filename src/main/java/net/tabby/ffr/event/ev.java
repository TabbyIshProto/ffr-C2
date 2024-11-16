package net.tabby.ffr.event;


import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.tabby.ffr.Tags;
import net.tabby.ffr.registry.ii;
import net.tabby.ffr.registry.sh;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class ev {

    @SubscribeEvent
    public static void onRightClickEvent(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stc = event.getItemStack();
        EntityPlayer ply = event.getEntityPlayer();
        if (stc.getItem() == Items.FLINT && !ply.getCooldownTracker().hasCooldown(stc.getItem())) {
            World wrl = event.getWorld();
            BlockPos pos = event.getPos();
            if (wrl.getBlockState(pos).getMaterial() == Material.ROCK) {
                EnumFacing face = event.getFace();
                if (face != EnumFacing.DOWN) {
                    Random r = new Random();
                    if (event.getSide() == Side.SERVER) {
                        if (face == EnumFacing.UP && r.nextFloat() >= 0.87f) {
                            dropFlintShard((int) Math.floor(next(r, 2f, 3.20f)), pos, face, wrl);
                            stc.shrink(1);
                            wrl.playSound(null, pos, sh.FLINT_KNAP, SoundCategory.BLOCKS, 1.1f, 1.0f + next(r, -0.15f, 0.15f));
                        } else if (r.nextFloat() >= 0.93f) {
                            face = face == null ? EnumFacing.UP : face;
                            dropFlintShard(2, pos, face, wrl);
                            stc.shrink(1);
                            wrl.playSound(null, pos, sh.FLINT_KNAP, SoundCategory.BLOCKS, 1.1f, 1.0f + next(r, -0.15f, 0.15f));
                        } else {
                            SoundEvent soun = r.nextFloat() >= 0.85f ? sh.FLINT_STRIKE_NANPA3 : r.nextFloat() >= 0.5f ? sh.FLINT_STRIKE_NANPA1 : sh.FLINT_STRIKE_NANPA2;
                            wrl.playSound(null, pos, soun, SoundCategory.BLOCKS, 1.0f, 1.0f + next(r, -0.15f, 0.15f));
                        }
                    }
                    ply.swingArm(event.getHand());
                    ply.getCooldownTracker().setCooldown(stc.getItem(), 5);
                }
            }
        }
    }

    protected static void dropFlintShard(int amount, BlockPos where, EnumFacing which, World inWorld) {
        Vec3d d = new Vec3d(which.getDirectionVec()).scale(0.5d);
        EntityItem eni = new EntityItem(inWorld, where.getX() + 0.5d + d.x, where.getY() + 0.5d + d.y, where.getZ() + 0.5d + d.z, new ItemStack(ii.FLINT_SHARD, amount));
        eni.setDefaultPickupDelay();
        inWorld.spawnEntity(eni);
    }

    public static float next(Random r, float lwr, float upr) {
        return lwr + r.nextFloat() * (upr - lwr);
    }



    @SubscribeEvent
    public static void lowerBreakingSpeed(PlayerEvent.BreakSpeed event) { //# TODO: for 4) same issue as vv but varying breakspeed based on item held
        EntityPlayer ply = event.getEntityPlayer();
        if (ply.getHeldItemMainhand().isEmpty() && !ply.isCreative()) {
            event.setNewSpeed(event.getOriginalSpeed() * 0.5f);
        }
    }



    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void preventWoodDrops(BlockEvent.HarvestDropsEvent event) { //# TODO: for 1) mining with a seed still gets you logs, for 2) logs can still be split into planks, sticks, for 3) pebbles and falling sand
        if (event.getHarvester() != null) {
            if (event.getState().getMaterial() == Material.WOOD) {
                EntityPlayer hrv = event.getHarvester();
                if (hrv.getHeldItemMainhand().isEmpty() && !hrv.isCreative()) {
                    event.getDrops().clear();
                } else if (hrv.getHeldItemMainhand().getItem().getIsRepairable(hrv.getHeldItemMainhand(), Items.FLINT.getDefaultInstance())) {
                    event.getDrops().clear();
                }
            } //# TODO: make table of which Material + tool + block combinations are and aren't mineable; should drop item...
        }
    }

    protected static <T> boolean nn(T thing) {
        return thing != null;
    }
}
