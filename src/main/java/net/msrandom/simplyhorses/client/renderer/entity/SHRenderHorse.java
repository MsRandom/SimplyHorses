package net.msrandom.simplyhorses.client.renderer.entity;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.ResourceLocation;
import net.msrandom.genetics.GenotypeHandler;
import net.msrandom.genetics.Locus;
import net.msrandom.simplyhorses.SimplyHorses;
import net.msrandom.simplyhorses.client.model.ModelHorseDraft;
import net.msrandom.simplyhorses.client.model.ModelHorseStandard;
import net.msrandom.simplyhorses.client.model.SHModelHorse;
import net.msrandom.simplyhorses.client.renderer.texture.SHMarkingTexture;
import net.msrandom.simplyhorses.entity.genetics.HorseGenetics.*;
import net.msrandom.simplyhorses.entity.SHEntityHorse;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static net.msrandom.simplyhorses.entity.SHEntityHorse.*;

public class SHRenderHorse extends RenderLiving<SHEntityHorse> {
    private static final SHModelHorse[] MODELS = new SHModelHorse[] {
            new ModelHorseStandard(),
            new ModelHorseDraft(),
            //TODO replace these with the correct models
            new ModelHorseStandard(),
            new ModelHorseStandard(),
            new ModelHorseStandard()
    };
    //Genetic hash -> pair of base texture and markings
    private static final Int2ObjectMap<ResourceLocation> CACHE = new Int2ObjectOpenHashMap<>();

    public SHRenderHorse(RenderManager manager) {
        super(manager, new ModelHorse(), 0.75F);
    }

    @Override
    public void doRender(SHEntityHorse entity, double x, double y, double z, float entityYaw, float partialTicks) {
        mainModel = entity.isChild() ? MODELS[entity.getVariant()].getFoal() : (ModelBase) MODELS[entity.getVariant()];
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(SHEntityHorse entity) {
        //Generate a unique hash value for the horse's genetics and type
        int hash = 1 << entity.getVariant() + 8;
        if (entity.isChild()) {
            hash |= 0x1; // First bit is for foal vs adult
        }
        hash |= entity.getBay().ordinal() << 1; // We use the 2 bits after to make sure all bay values are unique
        hash |= entity.getPalomino().ordinal() << 3; // Same thing for the 2 bits after with palomino
        hash |= entity.getChestnut().ordinal() << 5; // And 3 extra bits for chestnut
        for (DataParameter<Integer> genetic : SHEntityHorse.GENETICS) {
            hash = 31 * hash + entity.getDataManager().get(genetic);
        }

        //Get the textures from the cache based on the unique hash *and hope there is no conflicts*
        ResourceLocation texture = CACHE.get(hash);
        if (texture == null) {
            texture = new ResourceLocation(SimplyHorses.MOD_ID, "textures/entity/generated/horse_" + hash + ".png");
            Minecraft.getMinecraft().getTextureManager().loadTexture(texture, new SHMarkingTexture(new ResourceLocation(SimplyHorses.MOD_ID, "textures/entity/" + getBaseTexture(entity) + ".png"), generateMarkings(entity)));
            CACHE.put(hash, texture);
        } else {
            Minecraft.getMinecraft().getTextureManager().deleteTexture(texture);
            Minecraft.getMinecraft().getTextureManager().loadTexture(texture, new SHMarkingTexture(new ResourceLocation(SimplyHorses.MOD_ID, "textures/entity/" + getBaseTexture(entity) + ".png"), generateMarkings(entity)));
        }
        return texture;
    }

    //TODO NOT DONE
    private String getBaseTexture(SHEntityHorse entity) {
        final GenotypeHandler genotypeHandler = entity.getGenotypeHandler();
        StringBuilder builder = new StringBuilder();
        builder.append(entity.isChild() ? "foal/" : "adult/").append(entity.getTypeName()).append("/");
        if (genotypeHandler.get(GRAY).getDominant() == Gray.GRAY) {
            builder.append("gray");
        } else if (genotypeHandler.get(EXTENSION).getDominant() == Extension.BLACK) {
            if (genotypeHandler.get(AGOUTI).getDominant() == Agouti.AGOUTI) {
                final Locus<Cream> cream = genotypeHandler.get(CREAM);
                if (cream.getLeft() == Cream.CREAM && cream.getRight() == Cream.CREAM) {
                    builder.append("perlino");
                } else {
                    builder.append("bay/").append(entity.getBay().name().toLowerCase(Locale.ROOT));
                }
            } else {
                builder.append("black");
            }
        } else {
            final Locus<Cream> creamDilution = genotypeHandler.get(CREAM);
            if (creamDilution.getLeft() == Cream.CREAM && creamDilution.getRight() == Cream.CREAM) {
                builder.append("cremello");
            } else if (creamDilution.has(Cream.CREAM)) {
                builder.append("palomino/").append(entity.getPalomino().name().toLowerCase(Locale.ROOT));
            } else {
                builder.append("chestnut/").append(entity.getChestnut().name().toLowerCase(Locale.ROOT));
            }
        }

        return builder.toString();
    }

    //TODO not done
    private Set<ResourceLocation> generateMarkings(SHEntityHorse entity) {
        final Set<ResourceLocation> result = new HashSet<>();
        final GenotypeHandler genotypeHandler = entity.getGenotypeHandler();
        if (genotypeHandler.get(FRAME_OVERO).getDominant() == FrameOvero.FRAME_OVERO) {
            if (genotypeHandler.get(TOBIANO).getDominant() == Tobiano.TOBIANO) {
                result.add(getMarking(entity, "tovero/tovero_" + (entity.getTobiano() / 5 + 1)));
            } else {
                result.add(getMarking(entity, "frame_overo/frame_overo_" + (entity.getFrameOvero() + 1)));
            }
        } else if (genotypeHandler.get(TOBIANO).getDominant() == Tobiano.TOBIANO) {
            result.add(getMarking(entity, "tobiano/tobiano" + (entity.getTobiano() + 1)));
        }
        return result;
    }

    private ResourceLocation getMarking(SHEntityHorse entity, String marking) {
        String texture = "textures/entity/" + (entity.isChild() ? "foal/" : "adult/") + entity.getTypeName() + "/markings/" +
                marking + ".png";
        return new ResourceLocation(SimplyHorses.MOD_ID, texture);
    }
}
