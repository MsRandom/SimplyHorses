package net.msrandom.simplyhorses.client.renderer.entity;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.msrandom.simplyhorses.entity.SHEntityHorse;

public class SHMarkingsLayer implements LayerRenderer<SHEntityHorse> {
    private final SHRenderHorse renderer;

    public SHMarkingsLayer(SHRenderHorse renderer) {
        this.renderer = renderer;
    }

    @Override
    public void doRenderLayer(SHEntityHorse entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!entity.isInvisible()) {
            renderer.bindTexture(renderer.getTextures(entity).getSecond());
            renderer.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
