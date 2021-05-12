package net.msrandom.simplyhorses.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHorseStandard extends StandardHorseBase implements SHModelHorse {
    private final ModelBase foal = new Foal();

    public ModelHorseStandard() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.thighLeft = new ModelRenderer(this, 105, 57);
        this.thighLeft.setRotationPoint(7.5F, 3.0F, 25.0F);
        this.thighLeft.addBox(-2.0F, -1.0F, -4.0F, 4, 11, 7, 0.0F);
        this.neckTop = new ModelRenderer(this, 185, 2);
        this.neckTop.setRotationPoint(0.0F, -10.0F, 1.5F);
        this.neckTop.addBox(-1.5F, -1.5F, -2.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(neckTop, -0.5918411493512771F, 0.0F, 0.0F);
        this.legBackLeft = new ModelRenderer(this, 46, 57);
        this.legBackLeft.setRotationPoint(0.0F, 8.0F, 2.2F);
        this.legBackLeft.addBox(-1.5F, 0.0F, -1.5F, 3, 15, 4, 0.0F);
        this.earLeft = new ModelRenderer(this, 80, 18);
        this.earLeft.setRotationPoint(1.8F, -5.3F, 0.9F);
        this.earLeft.addBox(-1.0F, -2.5F, -0.5F, 2, 3, 1, 0.0F);
        this.neck = new ModelRenderer(this, 185, 15);
        this.neck.setRotationPoint(4.5F, 0.6F, 1.3F);
        this.neck.addBox(-2.0F, -10.2F, -4.0F, 4, 16, 8, 0.0F);
        this.setRotateAngle(neck, 0.6101112058880425F, 0.01756601953575551F, 0.0F);
        this.body = new ModelRenderer(this, 1, 97);
        this.body.setRotationPoint(-4.7F, -2.0F, -12.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 9, 11, 27, 0.0F);
        this.head = new ModelRenderer(this, 135, 0);
        this.head.setRotationPoint(0.0F, -7.3F, -0.25F);
        this.head.addBox(-3.0F, -5.1F, -4.4F, 6, 6, 6, 0.0F);
        this.setRotateAngle(head, 0.05235987755982988F, 0.0F, 0.0F);
        this.legFrontRight = new ModelRenderer(this, 0, 57);
        this.legFrontRight.setRotationPoint(1.6F, 11.0F, 3.2F);
        this.legFrontRight.addBox(-1.5F, 0.0F, -1.5F, 3, 15, 4, 0.0F);
        this.thighRight = new ModelRenderer(this, 136, 57);
        this.thighRight.setRotationPoint(1.5F, 3.0F, 25.0F);
        this.thighRight.addBox(-2.0F, -1.0F, -4.0F, 4, 11, 7, 0.0F);
        this.tail = new ModelRenderer(this, 190, 104);
        this.tail.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.tail.addBox(-1.5F, -2.0F, 0.0F, 2, 20, 5, 0.0F);
        this.setRotateAngle(tail, 0.05235987755982988F, 0.0F, 0.0F);
        this.tailTop = new ModelRenderer(this, 190, 80);
        this.tailTop.setRotationPoint(5.0F, 1.8F, 25.5F);
        this.tailTop.addBox(-1.0F, -2.0F, 0.0F, 1, 4, 5, 0.0F);
        this.earRight = new ModelRenderer(this, 80, 5);
        this.earRight.setRotationPoint(-1.8F, -5.3F, 0.9F);
        this.earRight.addBox(-1.0F, -2.5F, -0.5F, 2, 3, 1, 0.0F);
        this.legFrontLeft = new ModelRenderer(this, 24, 57);
        this.legFrontLeft.setRotationPoint(7.4F, 11.0F, 3.2F);
        this.legFrontLeft.addBox(-1.5F, 0.0F, -1.5F, 3, 15, 4, 0.0F);
        this.mouth = new ModelRenderer(this, 106, 18);
        this.mouth.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.mouth.addBox(-2.5F, -0.5F, -2.5F, 5, 1, 3, 0.0F);
        this.nose = new ModelRenderer(this, 133, 25);
        this.nose.setRotationPoint(0.0F, -2.6F, -7.4F);
        this.nose.addBox(-3.0F, -2.0F, -3.0F, 6, 4, 6, 0.0F);
        this.legBackRight = new ModelRenderer(this, 69, 57);
        this.legBackRight.setRotationPoint(0.1F, 8.0F, 2.2F);
        this.legBackRight.addBox(-1.5F, 0.0F, -1.5F, 3, 15, 4, 0.0F);
        this.jaw = new ModelRenderer(this, 108, 5);
        this.jaw.setRotationPoint(0.0F, -0.1F, -4.6F);
        this.jaw.addBox(-2.5F, -0.5F, -2.5F, 5, 1, 3, 0.0F);
        this.body.addChild(this.thighLeft);
        this.neck.addChild(this.neckTop);
        this.thighLeft.addChild(this.legBackLeft);
        this.head.addChild(this.earLeft);
        this.body.addChild(this.neck);
        this.neck.addChild(this.head);
        this.body.addChild(this.legFrontRight);
        this.body.addChild(this.thighRight);
        this.tailTop.addChild(this.tail);
        this.body.addChild(this.tailTop);
        this.head.addChild(this.earRight);
        this.body.addChild(this.legFrontLeft);
        this.jaw.addChild(this.mouth);
        this.head.addChild(this.nose);
        this.thighRight.addChild(this.legBackRight);
        this.head.addChild(this.jaw);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
    }

    @Override
    public ModelBase getFoal() {
        return foal;
    }

    private static class Foal extends StandardHorseBase {
        public ModelRenderer thighLeft_1;

        public Foal() {
            this.textureWidth = 48;
            this.textureHeight = 32;
            this.earRight = new ModelRenderer(this, 29, 0);
            this.earRight.setRotationPoint(-1.0F, -1.2F, -0.3F);
            this.earRight.addBox(-0.5F, -1.8F, -0.5F, 1, 2, 1, 0.0F);
            this.legFrontRight = new ModelRenderer(this, 0, 17);
            this.legFrontRight.setRotationPoint(1.1F, 4.0F, 1.8F);
            this.legFrontRight.addBox(-1.0F, 0.0F, -1.0F, 2, 13, 2, 0.0F);
            this.legFrontLeft = new ModelRenderer(this, 9, 17);
            this.legFrontLeft.setRotationPoint(3.8F, 4.0F, 1.8F);
            this.legFrontLeft.addBox(-1.0F, 0.0F, -1.0F, 2, 13, 2, 0.0F);
            this.neckTop = new ModelRenderer(this, 41, 8);
            this.neckTop.setRotationPoint(0.0F, -5.2F, 1.1F);
            this.neckTop.addBox(-0.5F, -1.8F, -1.0F, 1, 2, 2, 0.0F);
            this.setRotateAngle(neckTop, 0.7740535232594852F, 0.0F, 0.0F);
            this.tail = new ModelRenderer(this, 32, 10);
            this.tail.setRotationPoint(0.1F, -0.1F, 1.6F);
            this.tail.addBox(-1.1F, -1.0F, 0.0F, 2, 4, 2, 0.0F);
            this.setRotateAngle(tail, 0.1699950691442477F, 0.0F, 0.0F);
            this.legBackLeft = new ModelRenderer(this, 38, 17);
            this.legBackLeft.setRotationPoint(-0.5F, 3.6F, 1.6F);
            this.legBackLeft.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
            this.head = new ModelRenderer(this, 36, 1);
            this.head.setRotationPoint(0.0F, -0.8F, -0.2F);
            this.head.addBox(-1.5F, -1.5F, -2.6F, 3, 3, 3, 0.0F);
            this.setRotateAngle(head, -0.7740535232594852F, 0.0F, 0.0F);
            this.mouth = new ModelRenderer(this, 1, 5);
            this.mouth.setRotationPoint(0.0F, 0.0F, -2.0F);
            this.mouth.addBox(-1.0F, -0.5F, -1.2F, 2, 1, 2, 0.0F);
            this.legBackRight = new ModelRenderer(this, 29, 17);
            this.legBackRight.setRotationPoint(0.4F, 3.6F, 1.6F);
            this.legBackRight.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
            this.neck = new ModelRenderer(this, 20, 0);
            this.neck.setRotationPoint(2.5F, 1.0F, 1.7F);
            this.neck.addBox(-1.0F, -6.0F, -2.0F, 2, 6, 4, 0.0F);
            this.setRotateAngle(neck, 0.5462880558742251F, 0.0F, 0.0F);
            this.tailTop = new ModelRenderer(this, 33, 7);
            this.tailTop.setRotationPoint(2.5F, 0.9F, 9.2F);
            this.tailTop.addBox(-0.5F, -1.0F, 0.0F, 1, 1, 2, 0.0F);
            this.setRotateAngle(tailTop, 0.091106186954104F, 0.0F, 0.0F);
            this.thighLeft_1 = new ModelRenderer(this, 18, 16);
            this.thighLeft_1.setRotationPoint(0.7F, 1.4F, 9.3F);
            this.thighLeft_1.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
            this.earLeft = new ModelRenderer(this, 34, 0);
            this.earLeft.setRotationPoint(0.9F, -1.2F, -0.3F);
            this.earLeft.addBox(-0.5F, -1.8F, -0.5F, 1, 2, 1, 0.0F);
            this.body = new ModelRenderer(this, 0, 1);
            this.body.setRotationPoint(-2.5F, 7.0F, -6.0F);
            this.body.addBox(0.0F, 0.0F, 0.0F, 5, 5, 10, 0.0F);
            this.thighLeft = new ModelRenderer(this, 18, 24);
            this.thighLeft.setRotationPoint(4.3F, 1.4F, 9.3F);
            this.thighLeft.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
            this.nose = new ModelRenderer(this, 0, 0);
            this.nose.setRotationPoint(0.0F, -0.4F, -2.9F);
            this.nose.addBox(-1.0F, -1.0F, -2.7F, 2, 2, 3, 0.0F);
            this.jaw = new ModelRenderer(this, 2, 8);
            this.jaw.setRotationPoint(0.0F, 1.0F, -2.1F);
            this.jaw.addBox(-1.0F, -0.5F, -1.2F, 2, 1, 1, 0.0F);
            this.head.addChild(this.earRight);
            this.body.addChild(this.legFrontRight);
            this.body.addChild(this.legFrontLeft);
            this.neck.addChild(this.neckTop);
            this.tailTop.addChild(this.tail);
            this.thighLeft.addChild(this.legBackLeft);
            this.neckTop.addChild(this.head);
            this.jaw.addChild(this.mouth);
            this.thighLeft_1.addChild(this.legBackRight);
            this.body.addChild(this.neck);
            this.body.addChild(this.tailTop);
            this.body.addChild(this.thighLeft_1);
            this.head.addChild(this.earLeft);
            this.body.addChild(this.thighLeft);
            this.head.addChild(this.nose);
            this.head.addChild(this.jaw);
        }

        @Override
        public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
            this.body.render(f5);
        }
    }
}
