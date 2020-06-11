package net.msrandom.simplyhorses.horse.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.util.ResourceLocation;
import net.msrandom.simplyhorses.horse.EntitySHorse;

import java.util.HashMap;
import java.util.Map;

public class RenderSHorse extends RenderLiving<EntitySHorse> {
    private static final Map<String, ResourceLocation> CACHE = new HashMap<>();

    public RenderSHorse(RenderManager manager)
    {
        super(manager, new ModelHorse(), 0.75F);
    }

    protected ResourceLocation getEntityTexture(EntitySHorse entity) {
        String s = entity.getTexture();
        ResourceLocation resourcelocation = CACHE.get(s);

        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s);
            Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, new LayeredTexture(entity.getSpots()));
            CACHE.put(s, resourcelocation);
        }

        return resourcelocation;
    }
}
