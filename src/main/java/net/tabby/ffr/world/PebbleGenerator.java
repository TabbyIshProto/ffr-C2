package net.tabby.ffr.world;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class PebbleGenerator implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        for (int i = 0; i < 7; i++) {
            int wx = chunkX * 16 + random.nextInt(16) + 8;
            int wz = chunkZ * 16 + random.nextInt(16) + 8;
            for (int j = world.getActualHeight(); j > 0; j--) {
                BlockPos pos = new BlockPos(wx, j, wz);
                if (world.getBlockState(pos).isSideSolid(world, pos, EnumFacing.UP)) {
                    pebblify(world, pos, random);
                    break;
                }
            }
        }
    }

    protected void pebblify(World worldIn, BlockPos pos, Random ran) {
        worldIn.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
    }
}
