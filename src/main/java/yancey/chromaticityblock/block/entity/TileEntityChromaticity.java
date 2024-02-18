package yancey.chromaticityblock.block.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

import javax.annotation.Nullable;

public class TileEntityChromaticity extends TileEntity {

    public static final String KEY_COLOR = "color";

    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if (this.color != color) {
            this.color = color;
            markDirty();
        }
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger(KEY_COLOR, color);
        return nbtTagCompound;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        color = ItemBlockChromaticity.getColorFromNBT(nbtTagCompound);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 1, getUpdateTag());
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void handleUpdateTag(NBTTagCompound tag) {
        readFromNBT(tag);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

}
