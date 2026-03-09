package com.hbm.doors.block.entity;

import com.hbm.doors.door.DoorType;
import com.hbm.doors.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DoorBlockEntity extends BlockEntity {
    public static final byte STATE_CLOSED = 0;
    public static final byte STATE_OPEN = 1;
    public static final byte STATE_CLOSING = 2;
    public static final byte STATE_OPENING = 3;

    public byte state = STATE_CLOSED;
    public int openTicks = 0;
    private String doorTypeId = "large_vehicle_door";
    private boolean lastRedstoneState = false;

    public DoorBlockEntity(BlockPos pos, BlockState blockState, String doorTypeId) {
        super(ModBlockEntities.DOOR_BLOCK_ENTITY, pos, blockState);
        this.doorTypeId = doorTypeId;
    }

    public DoorBlockEntity(BlockPos pos, BlockState blockState) {
        this(pos, blockState, "large_vehicle_door");
    }

    public String getDoorTypeId() { return doorTypeId; }

    public @Nullable DoorType getDoorType() {
        return DoorType.fromId(doorTypeId);
    }

    public int getOpenTime() {
        DoorType type = getDoorType();
        return type != null ? type.getOpenTime() : 20;
    }

    public void toggle(World world, BlockPos pos, BlockState blockState) {
        if (state == STATE_CLOSED || state == STATE_CLOSING) {
            startOpening(world, pos);
        } else {
            startClosing(world, pos);
        }
    }

    public void setRedstone(boolean powered, World world, BlockPos pos, BlockState blockState) {
        if (powered == lastRedstoneState) return;
        lastRedstoneState = powered;
        if (powered) {
            if (this.state == STATE_CLOSED || this.state == STATE_CLOSING) {
                startOpening(world, pos);
            }
        } else {
            if (this.state == STATE_OPEN || this.state == STATE_OPENING) {
                startClosing(world, pos);
            }
        }
    }

    private void startOpening(World world, BlockPos pos) {
        state = STATE_OPENING;
        playSound(world, pos, getDoorType() != null ? getDoorType().getOpenSoundStart() : null);
        markDirty();
        sync(world);
    }

    private void startClosing(World world, BlockPos pos) {
        state = STATE_CLOSING;
        playSound(world, pos, getDoorType() != null ? getDoorType().getCloseSoundStart() : null);
        markDirty();
        sync(world);
    }

    private void playSound(World world, BlockPos pos, @Nullable SoundEvent sound) {
        if (sound != null) {
            world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, DoorBlockEntity be) {
        be.tick(world, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, DoorBlockEntity be) {
        // Client side animation is driven by openTicks received from server via sync packets
    }

    private void tick(World world, BlockPos pos, BlockState blockState) {
        int openTime = getOpenTime();
        boolean changed = false;
        if (this.state == STATE_OPENING) {
            if (openTicks < openTime) {
                openTicks++;
                changed = true;
            } else {
                this.state = STATE_OPEN;
                openTicks = openTime;
                playSound(world, pos, getDoorType() != null ? getDoorType().getOpenSoundEnd() : null);
                changed = true;
            }
        } else if (this.state == STATE_CLOSING) {
            if (openTicks > 0) {
                openTicks--;
                changed = true;
            } else {
                this.state = STATE_CLOSED;
                openTicks = 0;
                playSound(world, pos, getDoorType() != null ? getDoorType().getCloseSoundEnd() : null);
                changed = true;
            }
        }
        if (changed) {
            markDirty();
            sync(world);
        }
    }

    private void sync(World world) {
        if (!world.isClient && world instanceof ServerWorld sw) {
            sw.getChunkManager().markForUpdate(pos);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        state = nbt.getByte("state");
        openTicks = nbt.getInt("openTicks");
        if (nbt.contains("doorTypeId")) {
            doorTypeId = nbt.getString("doorTypeId");
        }
        lastRedstoneState = nbt.getBoolean("redstone");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putByte("state", state);
        nbt.putInt("openTicks", openTicks);
        nbt.putString("doorTypeId", doorTypeId);
        nbt.putBoolean("redstone", lastRedstoneState);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
