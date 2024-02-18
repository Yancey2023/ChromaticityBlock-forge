package yancey.chromaticityblock.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.block.entity.TileEntityChromaticity;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

import javax.annotation.Nullable;

public class BlockChromaticity extends BlockContainer {

    public BlockChromaticity() {
        super(Material.BARRIER);
        this.setBlockUnbreakable();
        this.setResistance(6000000);
        this.disableStats();
        this.translucent = true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    @SuppressWarnings(value = {"deprecation", "NullableProblems"})
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings(value = {"deprecation", "NullableProblems"})
    public float getAmbientOcclusionLightValue(IBlockState state) {
        return 1;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {

    }

    @Nullable
    @Override
    @SuppressWarnings("NullableProblems")
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityChromaticity();
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.getItem() == ChromaticityBlock.CHROMATICITY_BLOCK_ITEM) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEntityChromaticity) {
                ((TileEntityChromaticity) tileEntity).setColor(ItemBlockChromaticity.getColorFromItemStack(stack));
            }
        }
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        int color = 0xFF00FF00;
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityChromaticity) {
            color = ((TileEntityChromaticity) tileEntity).getColor();
        }
        return ItemBlockChromaticity.createWithColor(color);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean addLandingEffects(IBlockState state, WorldServer worldObj, BlockPos blockPosition, IBlockState iblockstate, EntityLivingBase entity, int numberOfParticles) {
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean addRunningEffects(IBlockState state, World world, BlockPos pos, Entity entity) {
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager) {
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(ItemBlockChromaticity.createWithColor(0xFF000000));
        items.add(ItemBlockChromaticity.createWithColor(0xFFFFFFFF));

        items.add(ItemBlockChromaticity.createWithColor(0xFFFF0000));
        items.add(ItemBlockChromaticity.createWithColor(0xFF00FF00));
        items.add(ItemBlockChromaticity.createWithColor(0xFF0000FF));

        items.add(ItemBlockChromaticity.createWithColor(0xFFFFFF00));
        items.add(ItemBlockChromaticity.createWithColor(0xFF00FFFF));
        items.add(ItemBlockChromaticity.createWithColor(0xFFFF00FF));

        items.add(ItemBlockChromaticity.createWithColor(0xFF8800FF));
        items.add(ItemBlockChromaticity.createWithColor(0xFF88FFFF));
        items.add(ItemBlockChromaticity.createWithColor(0xFFFF88FF));

        items.add(ItemBlockChromaticity.createWithColor(0xFFBA54EF));
    }

}
