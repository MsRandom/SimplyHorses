package net.msrandom.simplyhorses.client.renderer.texture;

import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.compress.utils.IOUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

public class SHMarkingTexture extends AbstractTexture {
    private final ResourceLocation base;
    public final Set<ResourceLocation> markings;

    public SHMarkingTexture(ResourceLocation base, Set<ResourceLocation> markings) {
        this.base = base;
        this.markings = markings;
    }

    public void loadTexture(IResourceManager resourceManager) throws IOException {
        this.deleteGlTexture();

        IResource baseResource = resourceManager.getResource(base);
        BufferedImage baseImage = TextureUtil.readBufferedImage(baseResource.getInputStream());
        IOUtils.closeQuietly(baseResource);

        for (ResourceLocation texture : this.markings) {
            IResource layerResource = resourceManager.getResource(texture);
            baseImage.getGraphics().drawImage(TextureUtil.readBufferedImage(layerResource.getInputStream()), 0, 0, null);
            IOUtils.closeQuietly(layerResource);
        }

        TextureUtil.uploadTextureImage(this.getGlTextureId(), baseImage);
    }
}
