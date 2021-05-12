package net.msrandom.simplyhorses.entity;

import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.msrandom.genetics.Allele;
import net.msrandom.genetics.GeneticsRegistry;
import net.msrandom.genetics.GeneticsRegistry.Gene;
import net.msrandom.genetics.GenotypeHandler;
import net.msrandom.genetics.Locus;
import net.msrandom.simplyhorses.entity.genetics.BayVariant;
import net.msrandom.simplyhorses.entity.genetics.ChestnutVariant;
import net.msrandom.simplyhorses.entity.genetics.HorseGenetics.*;
import net.msrandom.simplyhorses.entity.genetics.PalominoVariant;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SHEntityHorse extends AbstractHorse {
    public static final List<DataParameter<Integer>> GENETICS = new ArrayList<>();
    public static final DataParameter<Integer> VARIANTS = EntityDataManager.createKey(SHEntityHorse.class, DataSerializers.VARINT);
    private static final DataParameter<Byte> SHADE = EntityDataManager.createKey(SHEntityHorse.class, DataSerializers.BYTE);

    //These are here since we want GENETICS to be populated before the entity is created.
    private static final GeneticsRegistry REGISTRY = new GeneticsRegistry(GENETICS::size, () -> GENETICS.add(EntityDataManager.createKey(SHEntityHorse.class, DataSerializers.VARINT)));
    public static final Gene<Extension> EXTENSION = REGISTRY.register(Extension.class);
    public static final Gene<Agouti> AGOUTI = REGISTRY.register(Agouti.class);
    public static final Gene<Dun> DUN = REGISTRY.register(Dun.class);
    public static final Gene<Cream> CREAM = REGISTRY.register(Cream.class);
    public static final Gene<Champagne> CHAMPAGNE = REGISTRY.register(Champagne.class);
    public static final Gene<Silver> SILVER = REGISTRY.register(Silver.class);
    public static final Gene<Mushroom> MUSHROOM = REGISTRY.register(Mushroom.class);
    public static final Gene<Gray> GRAY = REGISTRY.register(Gray.class);
    public static final Gene<Tobiano> TOBIANO = REGISTRY.register(Tobiano.class);
    public static final Gene<LeopardComplex> LEOPARD_COMPLEX = REGISTRY.register(LeopardComplex.class);
    public static final Gene<FrameOvero> FRAME_OVERO = REGISTRY.register(FrameOvero.class);

    private final GenotypeHandler genotypeHandler = new GenotypeHandler(
            index -> dataManager.get(GENETICS.get(index)),
            (index, value) -> dataManager.set(GENETICS.get(index), value)
    );

    public SHEntityHorse(World world) {
        super(world);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        for (DataParameter<Integer> genetic : GENETICS) {
            dataManager.register(genetic, 0);
        }
        dataManager.register(VARIANTS, 0);
        dataManager.register(SHADE, (byte) 0);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            final Item item = player.getHeldItem(hand).getItem();
            if (item == Items.IRON_SHOVEL) {
                cycleGene(EXTENSION, hand);
            } else if (item == Items.IRON_PICKAXE) {
                cycleGene(AGOUTI, hand);
            } else if (item == Items.IRON_SWORD) {
                cycleGene(GRAY, hand);
            } else if (item == Items.IRON_AXE) {
                cycleGene(CREAM, hand);
            }
        }
        return super.processInteract(player, hand);
    }

    private <T extends Enum<T> & Allele> void cycleGene(Gene<T> gene, EnumHand hand) {
        try {
            final Method method = gene.getClass().getDeclaredMethod("getValues");
            method.setAccessible(true);
            final T[] values = (T[]) method.invoke(gene);
            final Locus<T> tLocus = genotypeHandler.get(gene);
            if (hand == EnumHand.MAIN_HAND) {
                final int ordinal = tLocus.getRight().ordinal();
                if (ordinal == values.length - 1) {
                    genotypeHandler.set(gene, tLocus.getLeft(), values[0]);
                } else {
                    genotypeHandler.set(gene, tLocus.getLeft(), values[ordinal + 1]);
                }
            } else {
                final int ordinal = tLocus.getLeft().ordinal();
                if (ordinal == values.length - 1) {
                    genotypeHandler.set(gene, values[0], tLocus.getRight());
                } else {
                    genotypeHandler.set(gene, values[ordinal + 1], tLocus.getRight());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setIntArray("Genetics", GENETICS.stream().mapToInt(genetic -> dataManager.get(genetic)).toArray());
        compound.setInteger("Variants", dataManager.get(VARIANTS));
        compound.setByte("Shade", dataManager.get(SHADE));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        int[] genetics = compound.getIntArray("Genetics");
        for (int i = 0; i < Math.min(genetics.length, GENETICS.size()); ++i) {
            dataManager.set(GENETICS.get(i), genetics[i]);
        }
        dataManager.set(VARIANTS, compound.getInteger("Variants"));
        dataManager.set(SHADE, compound.getByte("Shade"));
    }

    public int getVariant() {
        return 0;
    }

    public String getTypeName() {
        return "standard";
    }

    public BayVariant getBay() {
        // We get the number of the element relative to chestnut, then divide by the element size(6) to get a 0 to 1 value, which is then multiplied by 4 to get the position of the element relative to bay
        return BayVariant.VALUES[(int) Math.min(((dataManager.get(SHADE) + 1) / 6.0 * 4 - 1), 0)];
    }

    public PalominoVariant getPalomino() {
        return PalominoVariant.VALUES[dataManager.get(SHADE) / 2];
    }

    public ChestnutVariant getChestnut() {
        return ChestnutVariant.VALUES[dataManager.get(SHADE)];
    }

    private void setFrameOvero(int frameOvero) {
        dataManager.set(VARIANTS, dataManager.get(VARIANTS) | frameOvero);
    }

    public int getFrameOvero() {
        return dataManager.get(VARIANTS) & 0x7;
    }

    private void setTobiano(int tobiano) {
        dataManager.set(VARIANTS, dataManager.get(VARIANTS) | tobiano << 3);
    }

    public int getTobiano() {
        return dataManager.get(VARIANTS) >> 3 & 0x15;
    }

    public GenotypeHandler getGenotypeHandler() {
        return genotypeHandler;
    }
}
