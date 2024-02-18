package yancey.chromaticityblock.client.renderer.item.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yancey.chromaticityblock.client.renderer.block.entity.TileEntityChromaticityRenderer;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

@SideOnly(Side.CLIENT)
public class TileEntityChromaticityItemRenderer extends TileEntityItemStackRenderer {

    @Override
    @SuppressWarnings("NullableProblems")
    public void renderByItem(ItemStack itemStackIn, float partialTicks) {
        GlStateManager.pushMatrix();
        int color = ItemBlockChromaticity.getColorFromItemStack(itemStackIn);
        TileEntityChromaticityRenderer.render(color, 0, 0, 0);
        GlStateManager.popMatrix();
    }

}
