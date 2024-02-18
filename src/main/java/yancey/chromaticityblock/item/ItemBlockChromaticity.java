package yancey.chromaticityblock.item;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.block.entity.TileEntityChromaticity;

import javax.annotation.Nullable;
import java.util.Objects;

public class ItemBlockChromaticity extends ItemBlock {
    public ItemBlockChromaticity() {
        super(ChromaticityBlock.CHROMATICITY_BLOCK);
        setUnlocalizedName(ChromaticityBlock.CHROMATICITY_BLOCK.getUnlocalizedName());
        setRegistryName(Objects.requireNonNull(ChromaticityBlock.CHROMATICITY_BLOCK.getRegistryName()));
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public String getItemStackDisplayName(ItemStack stack) {
        return super.getItemStackDisplayName(stack) + " (#" + Integer.toHexString(getColorFromItemStack(stack)).toUpperCase() + ")";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setTileEntityItemStackRenderer(@Nullable TileEntityItemStackRenderer teisr) {
        super.setTileEntityItemStackRenderer(teisr);
    }

    public static int getColorFromItemStack(ItemStack stack) {
        return getColorFromNBT(stack.getOrCreateSubCompound("BlockEntityTag"));
    }

    public static int getColorFromNBT(NBTTagCompound nbt) {
        int color = 0xFF00FF00;
        if (nbt != null && nbt.hasKey(TileEntityChromaticity.KEY_COLOR, 3)) {
            color = nbt.getInteger(TileEntityChromaticity.KEY_COLOR);
        }
        return color;
    }

    public static ItemStack createWithColor(int color) {
        ItemStack itemStack = new ItemStack(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM);
        itemStack.getOrCreateSubCompound("BlockEntityTag").setInteger(TileEntityChromaticity.KEY_COLOR, color);
        return itemStack;
    }

}
