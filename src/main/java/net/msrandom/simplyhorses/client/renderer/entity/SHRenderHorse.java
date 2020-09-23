package net.msrandom.simplyhorses.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.msrandom.simplyhorses.client.model.ModelHorseDraft;
import net.msrandom.simplyhorses.client.model.ModelHorseStandard;
import net.msrandom.simplyhorses.client.model.SHModelHorse;
import net.msrandom.simplyhorses.entity.SHEntityHorse;

import java.util.HashMap;
import java.util.Map;

public class SHRenderHorse extends RenderLiving<SHEntityHorse> {
    private static final SHModelHorse[] MODELS = new SHModelHorse[] {
            new ModelHorseStandard(),
            new ModelHorseDraft(),
            //TODO replace these with the correct models
            new ModelHorseStandard(),
            new ModelHorseStandard(),
            new ModelHorseStandard()
    };
    private static final Map<String, ResourceLocation> CACHE = new HashMap<>();

    public SHRenderHorse(RenderManager manager)
    {
        super(manager, new ModelHorse(), 0.75F);
    }

    @Override
    public void doRender(SHEntityHorse entity, double x, double y, double z, float entityYaw, float partialTicks) {
        mainModel = entity.isChild() ? (ModelBase) MODELS[entity.getVariant()] : MODELS[entity.getVariant()].getFoal();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(SHEntityHorse entity) {
        //TODO replace this with layers
        return CACHE.computeIfAbsent(entity.getTexture(), ResourceLocation::new);
    }
}
