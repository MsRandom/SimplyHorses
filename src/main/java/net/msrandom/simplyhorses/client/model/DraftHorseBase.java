package net.msrandom.simplyhorses.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DraftHorseBase extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer legFrontLeft;
    public ModelRenderer legFrontRight;
    public ModelRenderer thighLeft;
    public ModelRenderer thighRight;
    public ModelRenderer neck;
    public ModelRenderer tailTop;
    public ModelRenderer legBackLeft;
    public ModelRenderer legBackRight;
    public ModelRenderer neckTop;
    public ModelRenderer head;
    public ModelRenderer nose;
    public ModelRenderer earLeft;
    public ModelRenderer earRight;
    public ModelRenderer jaw;
    public ModelRenderer mouth;
    public ModelRenderer tail;

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
