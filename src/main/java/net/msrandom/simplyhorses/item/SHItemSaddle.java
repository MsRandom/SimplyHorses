package net.msrandom.simplyhorses.item;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SHItemSaddle extends Item implements SHDyableItem {
    public SHItemSaddle() {
        setCreativeTab(CreativeTabs.TRANSPORTATION);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack saddle = new ItemStack(this);
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() instanceof BlockCauldron && !worldIn.isRemote) {
            BlockCauldron cauldron = (BlockCauldron) iblockstate.getBlock();
            int i = iblockstate.getValue(BlockCauldron.LEVEL);

            if (i > 0) {
                SHDyableItem.removeColor(saddle);
                cauldron.setWaterLevel(worldIn, pos, iblockstate, i - 1);
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.PASS;
    }
}
