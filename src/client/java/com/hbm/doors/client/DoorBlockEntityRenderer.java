package com.hbm.doors.client;

import com.hbm.doors.block.CustomDoorBlock;
import com.hbm.doors.block.entity.DoorBlockEntity;
import com.hbm.doors.client.obj.ObjLoader;
import com.hbm.doors.client.obj.ObjModel;
import com.hbm.doors.client.obj.ObjPart;
import com.hbm.doors.door.DoorType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class DoorBlockEntityRenderer implements BlockEntityRenderer<DoorBlockEntity> {
    public DoorBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(DoorBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        DoorType doorType = entity.getDoorType();
        if (doorType == null) return;

        float openProgress;
        byte state = entity.state;
        int openTicks = entity.openTicks;
        int openTime = doorType.getOpenTime();

        if (state == DoorBlockEntity.STATE_OPENING) {
            openProgress = (openTicks + tickDelta) / openTime;
        } else if (state == DoorBlockEntity.STATE_CLOSING) {
            openProgress = (openTicks - tickDelta) / openTime;
        } else if (state == DoorBlockEntity.STATE_OPEN) {
            openProgress = 1.0f;
        } else {
            openProgress = 0.0f;
        }
        openProgress = Math.max(0f, Math.min(1f, openProgress));
        float animTicks = openProgress * openTime;

        Identifier modelId = new Identifier("hbm_doors",
            "models/block/doors/" + doorType.getId() + ".obj");

        ObjModel model = ObjLoader.load(MinecraftClient.getInstance().getResourceManager(), modelId);
        if (model == null) return;

        matrices.push();

        Direction facing = entity.getCachedState().get(CustomDoorBlock.FACING);
        matrices.translate(0.5, 0, 0.5);
        float yRot = switch (facing) {
            case NORTH -> 180f;
            case SOUTH -> 0f;
            case WEST -> 90f;
            case EAST -> -90f;
            default -> 0f;
        };
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yRot));
        matrices.translate(-0.5, 0, -0.5);

        for (String partName : doorType.getPartNames()) {
            ObjPart part = model.getPart(partName);
            if (part == null) continue;

            float[] trans = new float[3];
            float[] rot = new float[4];
            float[] orig = new float[3];

            doorType.getTranslation(partName, animTicks, trans);
            doorType.getRotation(partName, animTicks, rot);
            doorType.getOrigin(partName, orig);

            matrices.push();

            matrices.translate(orig[0], orig[1], orig[2]);
            if (rot[3] != 0f) {
                matrices.multiply(RotationAxis.of(new org.joml.Vector3f(rot[0], rot[1], rot[2]))
                    .rotationDegrees(rot[3]));
            }
            matrices.translate(-orig[0], -orig[1], -orig[2]);
            matrices.translate(trans[0], trans[1], trans[2]);

            renderPart(part, matrices, vertexConsumers, light, overlay);

            matrices.pop();
        }

        matrices.pop();
    }

    private void renderPart(ObjPart part, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                            int light, int overlay) {
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getSolid());
        Matrix4f posMatrix = matrices.peek().getPositionMatrix();
        Matrix3f normalMatrix = matrices.peek().getNormalMatrix();

        for (int[][] face : part.faces) {
            if (face.length < 3) continue;
            for (int i = 1; i < face.length - 1; i++) {
                emitTriangle(consumer, part, posMatrix, normalMatrix, light, overlay,
                    face[0], face[i], face[i + 1]);
            }
        }
    }

    private void emitTriangle(VertexConsumer consumer, ObjPart part, Matrix4f posMatrix, Matrix3f normalMatrix,
                              int light, int overlay, int[] v0, int[] v1, int[] v2) {
        emitVertex(consumer, part, posMatrix, normalMatrix, light, overlay, v0);
        emitVertex(consumer, part, posMatrix, normalMatrix, light, overlay, v1);
        emitVertex(consumer, part, posMatrix, normalMatrix, light, overlay, v2);
    }

    private void emitVertex(VertexConsumer consumer, ObjPart part, Matrix4f posMatrix, Matrix3f normalMatrix,
                            int light, int overlay, int[] ref) {
        int vi = ref[0] - 1;
        int vti = ref[1] - 1;
        int vni = ref[2] - 1;

        float[] pos = (vi >= 0 && vi < part.vertices.size()) ? part.vertices.get(vi) : new float[]{0,0,0};
        float[] uv = (vti >= 0 && vti < part.texCoords.size()) ? part.texCoords.get(vti) : new float[]{0,0};
        float[] norm = (vni >= 0 && vni < part.normals.size()) ? part.normals.get(vni) : new float[]{0,1,0};

        consumer.vertex(posMatrix, pos[0], pos[1], pos[2])
            .color(200, 200, 200, 255)
            .texture(uv[0], 1.0f - uv[1])
            .overlay(overlay)
            .light(light)
            .normal(normalMatrix, norm[0], norm[1], norm[2])
            .next();
    }

    @Override
    public boolean rendersOutsideBoundingBox(DoorBlockEntity blockEntity) {
        return true;
    }
}
