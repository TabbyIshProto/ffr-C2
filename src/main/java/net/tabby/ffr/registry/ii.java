package net.tabby.ffr.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.tabby.ffr.Item.tool.unique.KnifeItem;
import net.tabby.ffr.Item.tool.Tier;

public class ii {
    /*
        with heavy inspiration from "no-tree-punching" mod by alcatrazEscapee, or https://github.com/alcatrazEscapee/no-tree-punching on github.
            esp:
                -flint shards
                -flint knapping
                -flint knifes, tools and usages
                -clay buckets/pottery
                -removal of stone/wood tool recipes
        this project was designed to take inspiration from said mod but ultimately to make its own experience of creating.
        go check out no-tree-punching it is also a neat-af mod :3
     */
    //# translation key required for en_us.lang to function :3
    public static final Item BONE_HANDLE = new Item().setRegistryName("handle_bone").setTranslationKey("handle_bone").setCreativeTab(CreativeTabs.COMBAT);
    public static final Item FLINT_SHARD = new Item().setRegistryName("shard_flint").setTranslationKey("shard_flint").setCreativeTab(CreativeTabs.MATERIALS);

    public static final Item FLINT_KNIFE = new KnifeItem(Tier.FLINT).setRegistryName("knife_flint").setTranslationKey("knife_flint").setCreativeTab(CreativeTabs.TOOLS);
    //TODO: granite mortar and pestle


    //# TODO: for 6) always drops, never doesn't drop, rarity off balance, for 7) add ways to gain grass fiber outside of breaking grass, such as crafting with sugarcane
    public static final Item GRASS_FIBER = new Item().setRegistryName("fiber_grass").setTranslationKey("fiber_grass").setCreativeTab(CreativeTabs.MATERIALS);
    //# experiemental ideas vv
    //# TODO: for 8) add calamari food for more balanced seafood experience, perhaps sushi and rice crops + seaweed after food expansion
    //# TODO: for 10) starch from mortar'd potatoes, for 11) automatic furnace-like mortar and pestle using wood pickaxes as wheel
}
