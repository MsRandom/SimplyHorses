package net.msrandom.simplyhorses.item;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.msrandom.simplyhorses.SimplyHorses;

public class SHItemSaddle extends Item {
    public SHItemSaddle() {
        setCreativeTab(CreativeTabs.TRANSPORTATION);
    }

    public static boolean hasColor(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("Color");
    }

    public static int getColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null) {
            if (nbttagcompound.hasKey("Color"))
                return nbttagcompound.getInteger("Color");
        }

        return 10511680;
    }

    public static void removeColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null) {
            if (nbttagcompound.hasKey("Color"))
                nbttagcompound.removeTag("Color");
        }
    }

    public static void setColor(ItemStack stack, int color) {
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setInteger("Color", color);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack saddle = new ItemStack(this);
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() instanceof BlockCauldron && this.hasColor(saddle) && !worldIn.isRemote) {
            BlockCauldron cauldron = (BlockCauldron) iblockstate.getBlock();
            int i = iblockstate.getValue(BlockCauldron.LEVEL);

            if (i > 0) {
                this.removeColor(saddle);
                cauldron.setWaterLevel(worldIn, pos, iblockstate, i - 1);
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.PASS;
    }
}
