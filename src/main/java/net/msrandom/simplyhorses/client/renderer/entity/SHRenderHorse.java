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
import net.msrandom.simplyhorses.entity.HorseGenetics;
import net.msrandom.simplyhorses.entity.SHEntityHorse;

import java.util.HashSet;
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
        int hash = 1 << entity.getVariant() + 1;
        if (entity.isChild()) {
            hash |= 0x1;
        }
        for (DataParameter<Integer> genetic : SHEntityHorse.GENETICS) {
            hash = 31 * hash + entity.getDataManager().get(genetic);
        }

        //Get the textures from the cache based on the unique hash *and hope there is no conflicts*
        ResourceLocation texture = CACHE.get(hash);
        if (texture == null) {
            //This will pretty much be a random name for the markings texture, but it doesn't matter as it's generated
            texture = new ResourceLocation(SimplyHorses.MOD_ID, hash + ".png");
            Minecraft.getMinecraft().getTextureManager().loadTexture(texture, new SHMarkingTexture(new ResourceLocation(SimplyHorses.MOD_ID, "textures/entity/" + getBaseTexture(entity) + ".png"), generateMarkings(entity)));
            CACHE.put(hash, texture);
        }
        return texture;
    }

    //TODO NOT DONE
    private String getBaseTexture(SHEntityHorse entity) {
        final GenotypeHandler genotypeHandler = entity.getGenotypeHandler();
        StringBuilder builder = new StringBuilder();
        builder.append(entity.isChild() ? "foal/" : "adult/").append(entity.getTypeName()).append("/");
        if (genotypeHandler.get(GRAY).getDominant() == HorseGenetics.Gray.GRAY) {
            builder.append("gray");
        } else if (genotypeHandler.get(EXTENSION).getDominant() == HorseGenetics.Extension.BLACK) {
            if (genotypeHandler.get(AGOUTI).getDominant() == HorseGenetics.Agouti.AGOUTI) {
                builder.append("bay");
            } else {
                builder.append("black");
            }
        } else {
            Locus<HorseGenetics.Cream> creamDilution = genotypeHandler.get(CREAM);
            if (creamDilution.getLeft() == HorseGenetics.Cream.CREAM && creamDilution.getRight() == HorseGenetics.Cream.CREAM) {
                builder.append("cremello");
            } else if (creamDilution.has(HorseGenetics.Cream.CREAM)) {
                builder.append("palomino");
            } else {
                builder.append("chestnut");
            }
        }

        return builder.toString();
    }

    @SuppressWarnings("unused")
    private Set<ResourceLocation> generateMarkings(SHEntityHorse entity) {
        return new HashSet<>(); //TODO
    }
}
