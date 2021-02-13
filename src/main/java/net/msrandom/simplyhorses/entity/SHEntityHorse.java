package net.msrandom.simplyhorses.entity;

import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.msrandom.genetics.GenotypeHandler;

import java.util.ArrayList;
import java.util.List;

public class SHEntityHorse extends AbstractHorse {
    public static final List<DataParameter<Integer>> GENETICS = new ArrayList<>();
    private final GenotypeHandler genotypeHandler = new GenotypeHandler(
            index -> dataManager.get(GENETICS.get(index)),
            (index, value) -> dataManager.set(GENETICS.get(index), value)
    );

    public SHEntityHorse(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        for (DataParameter<Integer> genetic : GENETICS) {
            dataManager.register(genetic, 0);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setIntArray("Genetics", GENETICS.stream().mapToInt(genetic -> dataManager.get(genetic)).toArray());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        int[] genetics = compound.getIntArray("Genetics");
        for (int i = 0; i < genetics.length; ++i) {
            if (i >= GENETICS.size()) createKey();
            dataManager.set(GENETICS.get(i), genetics[i]);
        }
    }

    public int getVariant() {
        return 0;
    }

    //TODO
    @SideOnly(Side.CLIENT)
    public String getTexture() {
        return "";
    }

    @SideOnly(Side.CLIENT)
    public String[] getSpots() {
        return new String[0];
    }

    public static void createKey() {
        GENETICS.add(EntityDataManager.createKey(SHEntityHorse.class, DataSerializers.VARINT));
    }
}
