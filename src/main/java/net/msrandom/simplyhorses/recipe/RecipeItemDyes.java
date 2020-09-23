package net.msrandom.simplyhorses.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.DyeUtils;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.msrandom.simplyhorses.item.SHDyableItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class RecipeItemDyes extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nullable World world) {
        ItemStack itemstack = ItemStack.EMPTY;
        boolean hasDye = false;

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack1 = inv.getStackInSlot(i);

            if (!itemstack1.isEmpty()) {
                if (itemstack1.getItem() instanceof SHDyableItem) {
                    if (!itemstack.isEmpty())
                        return false;

                    itemstack = itemstack1;

                } else {
                    if (!DyeUtils.isDye(itemstack1))
                        return false;

                    hasDye = true;
                }
            }
        }

        return !itemstack.isEmpty() && hasDye;
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inventoryCrafting) {
        ItemStack itemstack = ItemStack.EMPTY;
        int r = 0, g = 0, b = 0;
        int i = 0;
        int j = 0;
        boolean foundSaddle = false;

        for (int k = 0; k < inventoryCrafting.getSizeInventory(); ++k) {
            ItemStack itemstack1 = inventoryCrafting.getStackInSlot(k);

            if (!itemstack1.isEmpty()) {
                if (itemstack1.getItem() instanceof SHDyableItem) {
                    foundSaddle = true;

                    if (!itemstack.isEmpty())
                        return ItemStack.EMPTY;

                    itemstack = itemstack1.copy();
                    itemstack.setCount(1);

                    int l = SHDyableItem.getColor(itemstack);
                    float f = (l >> 16 & 255) / 255.0F;
                    float f1 = (l >> 8 & 255) / 255.0F;
                    float f2 = (l & 255) / 255.0F;
                    i = (int) (i + Math.max(f, Math.max(f1, f2)) * 255.0F);
                    r = (int) ((float) r + f * 255.0F);
                    g = (int) ((float) g + f1 * 255.0F);
                    b = (int) ((float) b + f2 * 255.0F);
                } else {
                    Optional<EnumDyeColor> color = DyeUtils.colorFromStack(itemstack1);
                    if (!color.isPresent()) return ItemStack.EMPTY;

                    float[] rgbNormalized = color.get().getColorComponentValues();
                    int l1 = (int) (rgbNormalized[0] * 255.0F);
                    int i2 = (int) (rgbNormalized[1] * 255.0F);
                    int j2 = (int) (rgbNormalized[2] * 255.0F);
                    i += Math.max(l1, Math.max(i2, j2));
                    r += l1;
                    g += i2;
                    b += j2;
                }
                ++j;
            }
        }

        if (!foundSaddle) return ItemStack.EMPTY;

        int i1 = r / j;
        int j1 = g / j;
        int k1 = b / j;
        float f3 = (float) i / (float) j;
        float f4 = (float) Math.max(i1, Math.max(j1, k1));
        i1 = (int) ((float) i1 * f3 / f4);
        j1 = (int) ((float) j1 * f3 / f4);
        k1 = (int) ((float) k1 * f3 / f4);
        int k2 = (i1 << 8) + j1;
        k2 = (k2 << 8) + k1;
        SHDyableItem.setColor(itemstack, k2);
        return itemstack;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            nonnulllist.set(i, ForgeHooks.getContainerItem(itemstack));
        }

        return nonnulllist;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @SuppressWarnings({"RedundantSuppression", "unused"})
    public static class Factory implements IRecipeFactory {
        @Override
        public IRecipe parse(final JsonContext context, final JsonObject json) {
            return new RecipeItemDyes();
        }
    }
}
