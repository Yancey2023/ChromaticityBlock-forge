package yancey.chromaticityblock;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yancey.chromaticityblock.client.renderer.item.entity.TileEntityChromaticityItemRenderer;

import java.util.Objects;

@EventBusSubscriber
public class RegisterHandler {

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(ChromaticityBlock.CHROMATICITY_BLOCK);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        ChromaticityBlock.CHROMATICITY_BLOCK_ITEM.setTileEntityItemStackRenderer(new TileEntityChromaticityItemRenderer());
        ModelLoader.setCustomModelResourceLocation(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM, 0,
                new ModelResourceLocation(Objects.requireNonNull(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM.getRegistryName()), "inventory"));
    }

}
