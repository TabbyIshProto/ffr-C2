package net.tabby.ffr.Item.recipe;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeRemover extends ForgeRegistries {
    public static final Set<?> UNDESIRED = Stream.of(
            Items.STONE_PICKAXE, Items.STONE_AXE, Items.STONE_HOE, Items.STONE_SHOVEL, Items.STONE_SWORD,
            Items.WOODEN_PICKAXE, Items.WOODEN_AXE, Items.WOODEN_HOE, Items.WOODEN_SHOVEL, Items.WOODEN_SWORD
    ).collect(Collectors.toSet());

    /*  thanks to Zamion101 on July 15th, 2017 on Forge-forums for finding out how to remove recipes
        <3 all credit goes to them for designing "RecipeRemover" [not loc] <3
     */

    public <T> RecipeRemover(T alike) {
        ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) RECIPES;
        recipeRegistry.remove(loc(alike));
        CraftingHelper.loadRecipes(false);
        CraftingManager.init();
    }

    private <T> ResourceLocation loc(T alike) {
        Item itm = alike instanceof Block ? Item.getItemFromBlock((Block) alike) : alike instanceof ItemStack ? ((ItemStack) alike).getItem() : (Item) alike;
        return itm.getRegistryName();
    }
}
