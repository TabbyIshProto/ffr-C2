package net.tabby.ffr;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tabby.ffr.Item.recipe.RecipeRemover;
import net.tabby.ffr.Item.tool.Tier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.12.2]")
public class FloraFaunaRebalance {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    @EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc. (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        // register to the event bus so that we can listen to events
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("I am " + Tags.MODNAME + " + at version " + Tags.VERSION);
        Tier.init();
    }

    @SubscribeEvent
    // Register recipes here (Remove if not needed)
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        Items.ROTTEN_FLESH.setContainerItem(Items.BEEF);
        //TODO:: bucket in furnace => salt, clay bucket
    }

    @SubscribeEvent
    // Register items here (Remove if not needed)
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ii.BONE_HANDLE, ii.FLINT_SHARD, ii.FLINT_KNIFE);
    }

    @SubscribeEvent
    public void registerItemRenders(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(ii.BONE_HANDLE, 0, new ModelResourceLocation(ii.BONE_HANDLE.getRegistryName(), "normal"));
        ModelLoader.setCustomModelResourceLocation(ii.FLINT_SHARD, 0, new ModelResourceLocation(ii.FLINT_SHARD.getRegistryName(), "normal"));
        ModelLoader.setCustomModelResourceLocation(ii.FLINT_KNIFE, 0, new ModelResourceLocation(ii.FLINT_KNIFE.getRegistryName(), "normal"));
    }

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(sh.FLINT_STRIKE_NANPA1, sh.FLINT_STRIKE_NANPA2, sh.FLINT_STRIKE_NANPA3, sh.FLINT_KNAP, sh.GRANITE_GRIND);
    }

    @SubscribeEvent
    // Register blocks here (Remove if not needed)
    public void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    @EventHandler
    // load "Do your mod setup. Build whatever data structures you care about." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        for (Object item : RecipeRemover.UNDESIRED) {
            new RecipeRemover(item);
        }

    }

    @EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
    }
}
