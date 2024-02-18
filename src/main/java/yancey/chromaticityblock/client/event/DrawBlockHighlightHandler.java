package yancey.chromaticityblock.client.event;

import net.minecraft.block.Block;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yancey.chromaticityblock.ChromaticityBlock;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = ChromaticityBlock.MOD_ID)
public class DrawBlockHighlightHandler {

    public static final DrawBlockHighlightHandler INSTANCE = new DrawBlockHighlightHandler();

    public DrawBlockHighlightHandler() {

    }

    @SubscribeEvent
    public void isCancelDrawBlockHighlight(DrawBlockHighlightEvent event) {
        RayTraceResult target = event.getTarget();
        if (target == null || target.typeOfHit != RayTraceResult.Type.BLOCK) {
            return;
        }
        Block block = event.getPlayer().world.getBlockState(target.getBlockPos()).getBlock();
        if (block == ChromaticityBlock.CHROMATICITY_BLOCK) {
            event.setCanceled(true);
        }
    }

}
