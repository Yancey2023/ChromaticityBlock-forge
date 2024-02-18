package yancey.chromaticityblock.client.renderer.block.entity;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yancey.chromaticityblock.block.entity.TileEntityChromaticity;

@SideOnly(Side.CLIENT)
public class TileEntityChromaticityRenderer extends TileEntitySpecialRenderer<TileEntityChromaticity> {

    @Override
    public void render(TileEntityChromaticity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        render(te.getColor(), (float) x, (float) y, (float) z);
    }

    public static void render(int color, float x, float y, float z) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        drawBox(0, 0, 0, 1, 1, 1,
                ((color >> 16) & 0xFF) / 255F, ((color >> 8) & 0xFF) / 255F,
                (color & 0xFF) / 255F, (color >> 24) & 0xFF);
        GlStateManager.popMatrix();
    }

    public static void drawBox(float x1, float y1, float z1, float x2, float y2, float z2, float red, float green, float blue, float alpha) {
        GlStateManager.pushAttrib();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0xF0, 0xF0);
        GlStateManager.color(red, green, blue, alpha);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);

        bufferBuilder.pos(x1, y1, z1).endVertex();
        bufferBuilder.pos(x1, y1, z2).endVertex();
        bufferBuilder.pos(x1, y2, z2).endVertex();
        bufferBuilder.pos(x1, y2, z1).endVertex();

        bufferBuilder.pos(x1, y1, z1).endVertex();
        bufferBuilder.pos(x2, y1, z1).endVertex();
        bufferBuilder.pos(x2, y1, z2).endVertex();
        bufferBuilder.pos(x1, y1, z2).endVertex();

        bufferBuilder.pos(x1, y1, z1).endVertex();
        bufferBuilder.pos(x1, y2, z1).endVertex();
        bufferBuilder.pos(x2, y2, z1).endVertex();
        bufferBuilder.pos(x2, y1, z1).endVertex();

        bufferBuilder.pos(x2, y1, z1).endVertex();
        bufferBuilder.pos(x2, y2, z1).endVertex();
        bufferBuilder.pos(x2, y2, z2).endVertex();
        bufferBuilder.pos(x2, y1, z2).endVertex();

        bufferBuilder.pos(x1, y2, z1).endVertex();
        bufferBuilder.pos(x1, y2, z2).endVertex();
        bufferBuilder.pos(x2, y2, z2).endVertex();
        bufferBuilder.pos(x2, y2, z1).endVertex();

        bufferBuilder.pos(x1, y1, z2).endVertex();
        bufferBuilder.pos(x2, y1, z2).endVertex();
        bufferBuilder.pos(x2, y2, z2).endVertex();
        bufferBuilder.pos(x1, y2, z2).endVertex();

        tessellator.draw();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.popAttrib();
    }

}
