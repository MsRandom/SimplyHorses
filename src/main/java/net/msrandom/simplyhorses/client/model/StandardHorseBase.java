package net.msrandom.simplyhorses.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class StandardHorseBase extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer legFrontRight;
    public ModelRenderer thighLeft;
    public ModelRenderer legFrontLeft;
    public ModelRenderer neck;
    public ModelRenderer tailTop;
    public ModelRenderer thighRight;
    public ModelRenderer legBackLeft;
    public ModelRenderer neckTop;
    public ModelRenderer head;
    public ModelRenderer nose;
    public ModelRenderer jaw;
    public ModelRenderer earRight;
    public ModelRenderer earLeft;
    public ModelRenderer mouth;
    public ModelRenderer tail;
    public ModelRenderer legBackRight;

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
