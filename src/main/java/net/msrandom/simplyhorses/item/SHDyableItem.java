package net.msrandom.simplyhorses.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface SHDyableItem {
    static int getColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null) {
            if (nbttagcompound.hasKey("Color"))
                return nbttagcompound.getInteger("Color");
        }

        return 0xA06540;
    }

    static void removeColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null) {
            if (nbttagcompound.hasKey("Color"))
                nbttagcompound.removeTag("Color");
        }
    }

    static void setColor(ItemStack stack, int color) {
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setInteger("Color", color);
    }
}
