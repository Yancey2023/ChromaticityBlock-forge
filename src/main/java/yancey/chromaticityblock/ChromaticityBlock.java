package yancey.chromaticityblock;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yancey.chromaticityblock.block.BlockChromaticity;
import yancey.chromaticityblock.block.entity.TileEntityChromaticity;
import yancey.chromaticityblock.client.event.DrawBlockHighlightHandler;
import yancey.chromaticityblock.client.renderer.block.entity.TileEntityChromaticityRenderer;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

@Mod(modid = ChromaticityBlock.MOD_ID, name = ChromaticityBlock.NAME, version = ChromaticityBlock.VERSION)
public class ChromaticityBlock {
    public static final String MOD_ID = "chromaticityblock";
    public static final String NAME = "Chromaticity Block";
    public static final String VERSION = "1.12.2-1.0-forge";

    public static final String CHROMATICITY_BLOCK_NAME = "chromaticity_block";

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(CreativeTabs.getNextID(), ChromaticityBlock.MOD_ID) {
        @SideOnly(Side.CLIENT)
        @SuppressWarnings("NullableProblems")
        public ItemStack getTabIconItem() {
            return new ItemStack(CHROMATICITY_BLOCK_ITEM);
        }
    };

    public static final Block CHROMATICITY_BLOCK = new BlockChromaticity()
            .setUnlocalizedName(CHROMATICITY_BLOCK_NAME)
            .setRegistryName(CHROMATICITY_BLOCK_NAME)
            .setCreativeTab(CREATIVE_TAB);

    public static final Item CHROMATICITY_BLOCK_ITEM = new ItemBlockChromaticity();

    @EventHandler
    @SuppressWarnings("deprecation")
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(DrawBlockHighlightHandler.INSTANCE);
        GameRegistry.registerTileEntity(TileEntityChromaticity.class, CHROMATICITY_BLOCK_NAME);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChromaticity.class, new TileEntityChromaticityRenderer());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }


}
