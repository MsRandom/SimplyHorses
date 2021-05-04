package net.msrandom.simplyhorses.client.renderer.entity;

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
    public final Set<ResourceLocation> textures;

    public SHMarkingTexture(Set<ResourceLocation> textures) {
        this.textures = textures;
    }

    public void loadTexture(IResourceManager resourceManager) throws IOException {
        this.deleteGlTexture();
        BufferedImage image = null;

        for (ResourceLocation texture : this.textures) {
            IResource resource = resourceManager.getResource(texture);
            BufferedImage layer = TextureUtil.readBufferedImage(resource.getInputStream());

            if (image == null) {
                image = new BufferedImage(layer.getWidth(), layer.getHeight(), 2);
            }

            image.getGraphics().drawImage(layer, 0, 0, null);
            IOUtils.closeQuietly(resource);
        }

        if (image != null) {
            TextureUtil.uploadTextureImage(this.getGlTextureId(), image);
        }
    }
}