package com.hbm_m.block.machines;

import org.jetbrains.annotations.Nullable;

import com.hbm_m.block.decorations.DoorBlock;
import com.hbm_m.block.entity.doors.DoorBlockEntity;
import com.hbm_m.block.entity.doors.DoorDecl;
import com.hbm_m.block.entity.doors.DoorDeclRegistry;
import com.hbm_m.block.entity.machines.UniversalMachinePartBlockEntity;
import com.hbm_m.multiblock.IMultiblockController;
import com.hbm_m.multiblock.IMultiblockPart;
import com.hbm_m.multiblock.MultiblockStructureHelper;
import com.hbm_m.multiblock.PartRole;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class UniversalMachinePartBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public UniversalMachinePartBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new UniversalMachinePartBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    private boolean isOrphaned(BlockGetter level, BlockPos pos) {
        if (!(level.getBlockEntity(pos) instanceof IMultiblockPart part)) {
            return false;
        }

        BlockPos controllerPos = part.getControllerPos();
        if (controllerPos == null) {
            return true;
        }

        BlockState controllerState = level.getBlockState(controllerPos);
        return !(controllerState.getBlock() instanceof IMultiblockController);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        BlockEntity be = pLevel.getBlockEntity(pPos);
        if (!(be instanceof IMultiblockPart part)) return Shapes.block();

        BlockPos controllerPos = part.getControllerPos();
        if (controllerPos == null) return Shapes.block();

        BlockState controllerState = pLevel.getBlockState(controllerPos);
        if (!(controllerState.getBlock() instanceof IMultiblockController controller)) return Shapes.block();

        if (controllerState.getBlock() instanceof DoorBlock doorBlock) {
            String declId = doorBlock.getDoorDeclId();
            DoorDecl decl = DoorDeclRegistry.getById(declId);
            if (decl != null && decl.getStructureDefinition() != null) {
                Direction facing = controllerState.getValue(DoorBlock.FACING);
                BlockPos worldOffset = pPos.subtract(controllerPos);
                BlockPos localOffset = MultiblockStructureHelper.rotateBack(worldOffset, facing);

                boolean isOpen = false;
                BlockEntity ctrlBe = pLevel.getBlockEntity(controllerPos);
                if (ctrlBe instanceof DoorBlockEntity doorBE) {
                    isOpen = doorBE.isOpen();
                }

                Map<BlockPos, VoxelShape> map = isOpen 
                    ? decl.getStructureDefinition().getOpenShapes() 
                    : decl.getStructureDefinition().getClosedShapes();

                VoxelShape shape = map.get(localOffset);
                if (shape != null) {
                    if (!shape.isEmpty()) {
                        return MultiblockStructureHelper.rotateShape(shape, facing);
                    }
                    return shape;
                }
            }

            Direction facing = controllerState.getValue(DoorBlock.FACING);
            VoxelShape masterShape = controller.getStructureHelper().generateShapeFromParts(facing);
            BlockPos worldOffset = pPos.subtract(controllerPos);
            return masterShape.move(-worldOffset.getX(), -worldOffset.getY(), -worldOffset.getZ());
        }

        Direction facing = controllerState.getValue(HorizontalDirectionalBlock.FACING);
        VoxelShape masterShape = controller.getStructureHelper().generateShapeFromParts(facing);
        BlockPos worldOffset = pPos.subtract(controllerPos);
        return masterShape.move(-worldOffset.getX(), -worldOffset.getY(), -worldOffset.getZ());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        BlockEntity be = pLevel.getBlockEntity(pPos);
        if (!(be instanceof IMultiblockPart part)) return Shapes.block();

        BlockPos controllerPos = part.getControllerPos();
        if (controllerPos == null) return Shapes.block();

        BlockState controllerState = pLevel.getBlockState(controllerPos);
        if (!(controllerState.getBlock() instanceof IMultiblockController controller)) return Shapes.block();

        if (controllerState.getBlock() instanceof DoorBlock doorBlock) {
            String declId = doorBlock.getDoorDeclId();
            DoorDecl decl = DoorDeclRegistry.getById(declId);
            if (decl != null && decl.getStructureDefinition() != null) {
                Direction facing = controllerState.getValue(DoorBlock.FACING);
                BlockPos worldOffset = pPos.subtract(controllerPos);
                BlockPos localOffset = MultiblockStructureHelper.rotateBack(worldOffset, facing);

                boolean isOpen = false;
                BlockEntity ctrlBe = pLevel.getBlockEntity(controllerPos);
                if (ctrlBe instanceof DoorBlockEntity doorBE) {
                    isOpen = doorBE.isOpen();
                }

                Map<BlockPos, VoxelShape> map = isOpen 
                    ? decl.getStructureDefinition().getOpenShapes() 
                    : decl.getStructureDefinition().getClosedShapes();

                VoxelShape shape = map.get(localOffset);
                if (shape != null) {
                    if (!shape.isEmpty()) {
                        return MultiblockStructureHelper.rotateShape(shape, facing);
                    }
                    return shape;
                }
            }
        }

        MultiblockStructureHelper helper = controller.getStructureHelper();
        Direction facing = controllerState.getValue(HorizontalDirectionalBlock.FACING);
        BlockPos worldOffset = pPos.subtract(controllerPos);
        BlockPos localOffset = MultiblockStructureHelper.rotateBack(worldOffset, facing);
        BlockPos gridPos = localOffset.offset(helper.getControllerOffset());

        return helper.getSpecificCollisionShape(gridPos, facing);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.getBlockEntity(pPos) instanceof IMultiblockPart part) {
            BlockPos controllerPos = part.getControllerPos();
            if (controllerPos == null) {
                if (!pLevel.isClientSide()) {
                    pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
                }
                return InteractionResult.sidedSuccess(pLevel.isClientSide());
            }

            BlockState controllerState = pLevel.getBlockState(controllerPos);
            if (controllerState.getBlock() instanceof IMultiblockController) {
                BlockEntity ctrlBe = pLevel.getBlockEntity(controllerPos);
                if (ctrlBe != null) {
                    return controllerState.getBlock().use(controllerState, pLevel, controllerPos, pPlayer, pHand, pHit);
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            if (pLevel.getBlockEntity(pPos) instanceof IMultiblockPart partBe) {
                BlockPos controllerPos = partBe.getControllerPos();
                if (controllerPos != null && !pLevel.isClientSide()) {
                    BlockState controllerState = pLevel.getBlockState(controllerPos);
                    if (controllerState.getBlock() instanceof IMultiblockController controller) {
                        if (controllerState.hasProperty(HorizontalDirectionalBlock.FACING)) {
                            Direction facing = controllerState.getValue(HorizontalDirectionalBlock.FACING);
                            controller.getStructureHelper().destroyStructure(pLevel, controllerPos, facing);
                            pLevel.setBlock(controllerPos, Blocks.AIR.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
}
