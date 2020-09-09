package net.msrandom.simplyhorses.item;

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

public class ItemCustomSaddle extends Item {
    protected String name;

    public ItemCustomSaddle(String name) {
        this.name = name;
        this.setTranslationKey(name);
        this.setRegistryName(new ResourceLocation(SimplyHorses.MOD_ID, name));
        setCreativeTab(CreativeTabs.TRANSPORTATION);
    }

    public boolean hasColor(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("color");
    }

    public int getColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null) {
            if (nbttagcompound.hasKey("color"))
                return nbttagcompound.getInteger("color");
        }

        return 10511680;
    }

    public void removeColor(ItemStack stack) { //todo use this by right clicking with item on water block/cauldron
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null) {
            if (nbttagcompound.hasKey("color"))
                nbttagcompound.removeTag("color");
        }
    }

    public void setColor(ItemStack stack, int color) {
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setInteger("color", color);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        /*ItemStack saddle = new ItemStack(this);
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() instanceof BlockCauldron && this.hasColor(saddle) && !worldIn.isRemote) {
            BlockCauldron cauldron = (BlockCauldron) iblockstate.getBlock();
            int i = iblockstate.getValue(BlockCauldron.LEVEL);

            if (i > 0) {
                this.removeColor(saddle);
                cauldron.setWaterLevel(worldIn, pos, iblockstate, i - 1);
                return EnumActionResult.SUCCESS;
            }
        }*/
        // todo, above code not recognizing colored saddles
        return EnumActionResult.PASS;
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(SimplyHorses.MOD_ID + ":" + name, "inventory"));
    }
}
